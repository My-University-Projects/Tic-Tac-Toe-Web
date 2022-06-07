package pl.polsl.tomasz.krypczyk.tictactoe.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.GameInfo;
import pl.polsl.tomasz.krypczyk.tictactoe.model.GameStatus;
import pl.polsl.tomasz.krypczyk.tictactoe.model.GameTableRow;
import pl.polsl.tomasz.krypczyk.tictactoe.repository.GameRepository;
import pl.polsl.tomasz.krypczyk.tictactoe.storage.GameStorage;

import java.util.ArrayList;
import java.util.List;

import static pl.polsl.tomasz.krypczyk.tictactoe.storage.GameStorage.getInstance;

@Service
@AllArgsConstructor
public class GamesTableService {

    @Autowired
    private GameRepository gameRepository;


    /**
     * Method that creates list of table rows for web page table
     *
     * @return List of table rows
     */
    public ArrayList<GameTableRow> createGamesList() {

        ArrayList<GameTableRow> gamesList = new ArrayList<>();
        GameStorage gameStorage = getInstance();

        if (gameStorage.getGames().isEmpty()) {
            return gamesList;
        }
        int id = 1;
        gameStorage.getGames().values().forEach(v -> {
            if (!v.getGameStatus().equals(GameStatus.FINISHED)) {
                GameTableRow gameTableRow = new GameTableRow();
                gameTableRow.setFirstPlayerName(v.getPlayer1().getPlayerName());
                gameTableRow.setSecondPlayerName(v.getPlayer2().getPlayerName());
                gameTableRow.setGameStatus(v.getGameStatus());
                gameTableRow.setId(id);
                gamesList.add(gameTableRow);
                id += 1;
            }
        });
        return gamesList;
    }

    private ArrayList<GameTableRow> fillGamesList() {
        ArrayList<GameTableRow> gamesList = new ArrayList<>();
        List<GameInfo> gameInfoList = gameRepository.getAll();
        int id = 1;
        gameInfoList.forEach(gil -> {
            GameTableRow gameTableRow = new GameTableRow();
            gameTableRow.setId(id);
            gameTableRow.setFirstPlayerName(gil.getFirstPlayerName());
            gameTableRow.setSecondPlayerName(gil.getSecondPlayerName());
            gameTableRow.setGameStatus(toGameStatus(gil.getStatus()));
            gamesList.add(gameTableRow);
        });
        return gamesList;
    }

    /**
     * Simple convert to GameStatus enum method
     *
     * @param gameStatus game status as string
     * @return game status as enum
     */
    private GameStatus toGameStatus(String gameStatus) {
        switch (gameStatus) {
            case "STARTED" -> {
                return GameStatus.STARTED;
            }
            case "IN_PROGRESS" -> {
                return GameStatus.IN_PROGRESS;
            }
            default -> {
                return GameStatus.FINISHED;
            }
        }
    }
}
