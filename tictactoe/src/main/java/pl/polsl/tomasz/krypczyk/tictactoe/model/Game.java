package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.Data;

/**
 * Class representanting a game object
 */
@Data
public class Game {

    /**
     * first player field
     */
    private Player player1;

    /**
     * second player field
     */
    private Player player2;

    /**
     * field with a game id
     */
    private String gameId;

    /**
     * field with actual game status
     */
    private GameStatus gameStatus;

    /**
     * field with game board
     */
    private Board board;

    /**
     * field with winning moves list
     */
    private WinningConditions winningConditions;

    /**
     * field with actual round number
     */
    private int roundCount;

    /**
     * field with winners name
     */
    private String winnersName;

    /**
     * Constructor
     */
    public Game() {
        this.board = new Board();
        this.winningConditions = new WinningConditions();
        this.roundCount = 1;
    }

}
