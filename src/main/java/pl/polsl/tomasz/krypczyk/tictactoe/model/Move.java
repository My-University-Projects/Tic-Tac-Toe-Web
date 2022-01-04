package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.Data;

/**
 * Class contains necessary informations about players move
 */
@Data
public class Move {

    /**
     * field with players type O or X
     */
    private String type;

    /**
     * field with position taken by the player on the board
     */
    private int position;

    /**
     * field with game id
     */
    private String gameId;

}
