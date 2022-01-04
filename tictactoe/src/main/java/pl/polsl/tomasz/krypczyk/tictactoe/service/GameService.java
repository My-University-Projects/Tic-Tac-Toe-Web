package pl.polsl.tomasz.krypczyk.tictactoe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.GameNotFoundException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.InvalidGameIdException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.Player2ExistsException;
import pl.polsl.tomasz.krypczyk.tictactoe.model.*;
import pl.polsl.tomasz.krypczyk.tictactoe.storage.GameStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Class contains game logic and monitors players moves
 */
@Service
@AllArgsConstructor
public class GameService {

    /**
     * Method that creates a new game
     * @param player1 first player who has created game
     * @return new game object
     */
    public Game createGame(Player player1) {
        Game game = new Game();
        game.setGameId(UUID.randomUUID().toString());
        Player player = new Player();
        player.setPlayerName(player1.getPlayerName());
        player.setSign("O");
        player.setPositions(new ArrayList<>());
        game.setPlayer1(player);
        game.setGameStatus(GameStatus.STARTED);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * Method that connects second player to the existing game by game id
     * @param player2Name second player
     * @param gameId game id put on the form
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
     * @param player2Name second player
     * @return updated game object
     * @throws GameNotFoundException
     */
    public Game connectToRandomGame(String player2Name) throws GameNotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(gs -> gs.getGameStatus().equals(GameStatus.STARTED))
                .findFirst()
                .orElseThrow(() -> new GameNotFoundException());

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
     * Method that checks moves, updates board and chooses winner of the game
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
        if(game.getRoundCount() % 2 == 1){
            player = game.getPlayer1();
        }
        else{
            player = game.getPlayer2();
        }

        if(player.getSign().equals(move.getType())){
            GameStorage.getInstance().setGame(game);
            return game;
        }

        Board board = game.getBoard();
        if(board.updateBoard(player, move.getPosition())) {
            player.addPosition(move.getPosition());
            game.setRoundCount(game.getRoundCount() + 1);
        }
        else {
            // powtorka tego samego gracza
        }
        if (game.getWinningConditions().checkWinConditions(player)) {
            game.setWinnersName(player.getPlayerName());
            game.setGameStatus(GameStatus.FINISHED);
        }
        if(game.getRoundCount() > 9){
            game.setGameStatus(GameStatus.FINISHED);
        }
        GameStorage.getInstance().setGame(game);
        return game;
    }
}
