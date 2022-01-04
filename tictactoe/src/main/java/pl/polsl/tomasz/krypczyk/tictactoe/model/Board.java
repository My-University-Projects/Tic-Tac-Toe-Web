package pl.polsl.tomasz.krypczyk.tictactoe.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representanting a game board model
 */
@Data
public class Board {

    /**
     * 2D array with signs X or O
     */
    private String[][] board;

    /**
     * constructor of the board
     */
    public Board() {
        this.createEmptyBoard();
    }

    /**
     * Method that creates empty board
     */
    private void createEmptyBoard()
    {
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
           for(int j = 0; j < 3; j++){
               board[i][j] = "";
           }
        }
    }

    /**
     * calculateColumn calculates depending on chosen by the player position
     *
     * @param position that players wants to put sign on
     * @return int the column on the game board
     */
    public int calculateColumn(int position) {

        int column = switch (position) {
            case 1, 4, 7 -> 0;
            case 2, 5, 8 -> 1;
            case 3, 6, 9 -> 2;
            default -> -1;
        };
        return column;
    }

    /**
     * checkIfEmpty checks that actual position is avaible for player to put
     * sign on
     *
     * @param position that players wants to put sign on
     * @return boolean true if positon is empty
     */
    public boolean checkIfEmpty(String position) {
        return position == "";
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
            if (checkIfEmpty(this.board[row][column])) {
                this.board[row][column] =  player.getSign();
                result = true;
            } else {
                result = false;
            }
        } else if (el <= 6 && el >= 4) {
            row = 1;
            column = this.calculateColumn(el);
            if (checkIfEmpty(this.board[row][column])) {
                this.board[row][column] =  player.getSign();
                result = true;
            } else {
                result = false;
            }
        } else if (el <= 9 && el >= 7) {
            row = 2;
            column = this.calculateColumn(el);
            if (checkIfEmpty(this.board[row][column])) {
                this.board[row][column] =  player.getSign();
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

