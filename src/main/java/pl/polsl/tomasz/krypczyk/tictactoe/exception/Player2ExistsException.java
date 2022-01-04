package pl.polsl.tomasz.krypczyk.tictactoe.exception;

/**
 * Exception is thrown when second players is alredy in a specified game
 */
public class Player2ExistsException extends Exception {

    public Player2ExistsException() {
        super("Drugi gracz już jest w grze!");
    }
}
