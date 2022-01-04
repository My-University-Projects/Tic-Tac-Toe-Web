package pl.polsl.tomasz.krypczyk.tictactoe.exception;

/**
 * Exception is thrown when player has put wrong game id
 */
public class InvalidGameIdException extends Exception {

    public InvalidGameIdException() {
        super("Błędne Id gry!");
    }
}
