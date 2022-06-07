package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class representanting a game board model
 */
@Data
public class Board {

    /**
     * 2D array with signs X or O
     */
    private List<List<String>> board;

    /**
     * constructor of the board
     */
    public Board() {
        this.createEmptyBoard();
    }

    /**
     * Method that creates empty board
     */
    private void createEmptyBoard() {
        this.board = Arrays.asList(Arrays.asList("", "", ""), Arrays.asList("", "", ""), Arrays.asList("", "", ""));
    }

    /**
     * calculateColumn calculates depending on chosen by the player position
     *
     * @param position that players wants to put sign on
     * @return int the column on the game board
     */
    public int calculateColumn(int position) {

        return switch (position) {
            case 1, 4, 7 -> 0;
            case 2, 5, 8 -> 1;
            case 3, 6, 9 -> 2;
            default -> -1;
        };
    }

    /**
     * checkIfEmpty checks that actual position is avaible for player to put
     * sign on
     *
     * @param position that players wants to put sign on
     * @return boolean true if positon is empty
     */
    public boolean isEmpty(String position) {
        return Objects.equals(position, "");
    }

    /**
     * updateBoard puts on a board sign in the chosen by player position
     *
     * @param player actual player
     * @param el     chosen by the player board element
     * @return boolean if update has been finished succesful
     */
    public boolean updateBoard(Player player, int el) {
        boolean result;
        int row, column;
        if (el <= 3 && el >= 1) {
            row = 0;
            column = this.calculateColumn(el);
            if (isEmpty(this.board.get(row).get(column))) {
                this.board.get(row).set(column, player.getSign());
                result = true;
            } else {
                result = false;
            }
        } else if (el <= 6 && el >= 4) {
            row = 1;
            column = this.calculateColumn(el);
            if (isEmpty(this.board.get(row).get(column))) {
                this.board.get(row).set(column, player.getSign());
                result = true;
            } else {
                result = false;
            }
        } else if (el <= 9 && el >= 7) {
            row = 2;
            column = this.calculateColumn(el);
            if (isEmpty(this.board.get(row).get(column))) {
                this.board.get(row).set(column, player.getSign());
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }
}

