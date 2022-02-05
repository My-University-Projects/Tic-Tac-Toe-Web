package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Game table row class
 */
@Data
public class GameTableRow {

    /**
     * id field
     */
    int id;
    /**
     * First player name field
     */
    String firstPlayerName;

    /**
     * Second player name field
     */
    String secondPlayerName;

    /**
     * Game status field
     */
    GameStatus gameStatus;

}
