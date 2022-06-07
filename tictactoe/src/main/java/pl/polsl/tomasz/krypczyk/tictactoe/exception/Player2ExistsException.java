package pl.polsl.tomasz.krypczyk.tictactoe.exception;

/**
 * Exception is thrown when second players is already in a specified game
 */
public class Player2ExistsException extends Exception {

    public Player2ExistsException() {
        super("Second player is in this game already!");
    }
}
