package pl.polsl.tomasz.krypczyk.tictactoe.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.GameInfo;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.Moves;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.GameNotFoundException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.InvalidGameIdException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.Player2ExistsException;
import pl.polsl.tomasz.krypczyk.tictactoe.model.*;
import pl.polsl.tomasz.krypczyk.tictactoe.repository.GameRepository;
import pl.polsl.tomasz.krypczyk.tictactoe.repository.MovesRepository;
import pl.polsl.tomasz.krypczyk.tictactoe.storage.GameStorage;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class contains game logic and monitors players moves
 */
@Service
@AllArgsConstructor
public class GameService {

    /**
     * Moves repository field
     */
    @Autowired
    private MovesRepository movesRepository;

    /**
     * Games repository field
     */
    @Autowired
    private GameRepository gameRepository;

    /**
     * Method that creates a new game
     *
     * @param player1 first player who has created game
     * @return new game object
     */
    public Game createGame(Player player1) {
        Game game = new Game();
        game.setGameId(UUID.randomUUID().toString());

        Player tmp = new Player();
        tmp.setPlayerName("-");
        game.setPlayer2(tmp);
        Player player = new Player();
        player.setPlayerName(player1.getPlayerName());
        player.setSign("O");
        player.setPositions(new ArrayList<>());
        game.setPlayer1(player);

        game.setGameStatus(GameStatus.STARTED);
        GameStorage.getInstance().setGame(game);
        saveGameToDatabase(game);

        return game;
    }

    /**
     * Method that connects second player to the existing game by game id
     *
     * @param player2Name second player
     * @param gameId      game id put on the form
     * @return updated game object
     * @throws InvalidGameIdException
     * @throws Player2ExistsException
     */
    public Game connectToGame(String player2Name, String gameId) throws InvalidGameIdException, Player2ExistsException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidGameIdException();
        }

        Game game = GameStorage.getInstance().getGames().get(gameId);
        if (game.getPlayer2() != null) {
            throw new Player2ExistsException();
        }
        Player player2 = new Player();
        player2.setPlayerName(player2Name);
        player2.setSign("X");
        player2.setPositions(new ArrayList<>());
        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * Method that connects second player to the random existing game
     *
     * @param player2Name second player
     * @return updated game object
     * @throws GameNotFoundException
     */
    public Game connectToRandomGame(String player2Name) throws GameNotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream().filter(gs -> gs.getGameStatus().equals(GameStatus.STARTED)).findFirst().orElseThrow(() -> new GameNotFoundException());

        Player player2 = new Player();
        player2.setPlayerName(player2Name);
        player2.setSign("X");
        player2.setPositions(new ArrayList<>());
        game.setPlayer2(player2);

        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        updateSecondPlayerInfo(game);

        return game;
    }

    /**
     * Method that checks moves, updates board and chooses winner of the game
     *
     * @param move actual move information
     * @return updated game object
     * @throws GameNotFoundException
     */
    public Game move(Move move) throws GameNotFoundException {
        if (!GameStorage.getInstance().getGames().containsKey(move.getGameId())) {
            throw new GameNotFoundException();
        }
        Game game = GameStorage.getInstance().getGames().get(move.getGameId());
        if (game.getGameStatus() == GameStatus.FINISHED) {
            throw new GameNotFoundException();
        }
        Player player;
        String positionsToString;
        if (game.getRoundCount() % 2 == 1) {
            player = game.getPlayer1();
        } else {
            player = game.getPlayer2();
        }
        if (player.getSign().equals(move.getType())) {
            GameStorage.getInstance().setGame(game);
            return game;
        }
        Board board = game.getBoard();
        if (board.updateBoard(player, move.getPosition())) {
            player.addPosition(move.getPosition());
            positionsToString = player.positionsToString();
            updateMoves(game.getRoundCount(), game, positionsToString);
            game.setRoundCount(game.getRoundCount() + 1);
        }
        if (game.getWinningConditions().checkWinConditions(player)) {
            game.setWinnersName(player.getPlayerName());
            game.setGameStatus(GameStatus.FINISHED);
            gameRepository.updateFinishStatus(game.getGameId().toString(), game.getGameStatus().toString());
        }
        if (game.getRoundCount() > 9) {
            game.setGameStatus(GameStatus.FINISHED);
            gameRepository.updateFinishStatus(game.getGameId().toString(), game.getGameStatus().toString());
        }
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * Method that saves new game into database
     *
     * @param game game
     */
    private void saveGameToDatabase(Game game) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setFirstPlayerName(game.getPlayer1().getPlayerName());
        gameInfo.setSecondPlayerName(game.getPlayer2().getPlayerName());
        gameInfo.setGameUUID(game.getGameId());

        Moves moves = new Moves();
        gameInfo.setMoves(moves);
        gameInfo.setStatus(game.getGameStatus().toString());
        moves.setGame(gameInfo);
        movesRepository.save(moves);
        gameRepository.save(gameInfo);
    }

    /**
     * Method that updates game info with second player name
     *
     * @param game actual game
     */
    private void updateSecondPlayerInfo(Game game) {
        gameRepository.setSecondPlayerNameAndStatus(game.getGameId(), game.getPlayer2().getPlayerName(), game.getGameStatus().toString());
    }

    /**
     * Method that updates moves table in database
     *
     * @param roundCount        actual round
     * @param game              actual game
     * @param positionsToString players positions as string
     */
    private void updateMoves(int roundCount, Game game, String positionsToString) {
        if (roundCount % 2 == 1) {
            movesRepository.updateFirstPlayerMoves(game.getGameId().toString(), positionsToString);
        } else {
            movesRepository.updateSecondPlayerMoves(game.getGameId().toString(), positionsToString);
        }
    }
}
