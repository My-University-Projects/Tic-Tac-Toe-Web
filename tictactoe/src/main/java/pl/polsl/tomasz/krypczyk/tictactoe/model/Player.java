package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.Data;

import java.util.List;

/**
 * Class representanting a player object
 */
@Data
public class Player {

    /**
     * field with player name
     */
    private String playerName;

    /**
     * field with player sign
     */
    private String sign;

    /**
     * field with taken positions
     */
    private List<Integer> positions;

    /**
     * method that adds new position to the positions list
     *
     * @param position taken position 1 - 9
     */
    public void addPosition(int position) {

        this.positions.add(position);

    }

    public String positionsToString() {
        StringBuilder positionsToString = new StringBuilder();
        if (this.positions.isEmpty()) {
            return positionsToString.toString();
        }

        this.positions.forEach(p -> {
            positionsToString.append(p.toString()).append(" ");
        });

        return positionsToString.toString();
    }
}
