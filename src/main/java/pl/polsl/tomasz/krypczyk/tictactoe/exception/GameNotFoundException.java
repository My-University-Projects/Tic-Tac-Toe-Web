package pl.polsl.tomasz.krypczyk.tictactoe.exception;

/**
 * Exception is thrown when specified game is not found
 */
public class GameNotFoundException extends Exception {

    public GameNotFoundException() {
        super("Nie znaleziono gry!");
    }
}
