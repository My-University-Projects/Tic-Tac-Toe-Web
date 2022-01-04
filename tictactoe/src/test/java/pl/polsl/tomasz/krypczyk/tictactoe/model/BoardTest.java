package pl.polsl.tomasz.krypczyk.tictactoe.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    /**
     * Test of calculateColumn method
     * @param param positive input
     */
    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void testCalculateColumnWithPositiveInput(int param) {
        //GIVEN
        Board board = new Board();
        int expResult = switch (param) {
            case 1, 7, 4 ->
                    0;
            case 2, 8, 5 ->
                    1;
            case 3, 9, 6 ->
                    2;
            default ->
                    -1;
        };

        //WHEN
        int calculatedColumn = board.calculateColumn(param);

        //THEN
        assertEquals(expResult, calculatedColumn);
    }

    /**
     * Test of calculateColumn test
     * @param param negative input
     */
    @ParameterizedTest
    @ValueSource (ints = {-1, 0, 323, 4232425, 521, 61, -907, 84, 91})
    void testCalculateColumnWithNegativeInput(int param) {
        //GIVEN
        Board board = new Board();
        int expResult = -1;

        //WHEN
        int calculatedColumn = board.calculateColumn(param);

        //THEN
        assertEquals(expResult, calculatedColumn);
    }

    /**
     * Test of checkIfEmpty method
     */
    @Test
    void testCheckIfEmptyWhenEmpty() {

        //GIVEN
        Board board = new Board();
        String position = "";

        //WHEN
       boolean result = board.checkIfEmpty(position);

        //THEN
        assertTrue(result);
    }

    /**
     * Test of checkIfEmpty method
     */
    @Test
    void testCheckIfEmptyWhenNotEmpty() {

        //GIVEN
        Board board = new Board();
        String position = "X";

        //WHEN
        boolean result = board.checkIfEmpty(position);

        //THEN
        assertFalse(result);
    }


    /**
     * Test of upadarteBoard method
     * @param param positive input
     */
    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void testUpdateBoardWithPositiveInput(int param) {
        //GIVEN
        Board board = new Board();
        Player player = new Player();
        player.setSign("X");
        int el = param;

        //WHEN
        boolean result = board.updateBoard(player, el);

        //THEN
        assertTrue(result);
    }

    /**
     * Test of updateBoard method
     * @param param negative input
     */
    @ParameterizedTest
    @ValueSource (ints = {-1, 212, 0, 423, -3235, 2323236, -1, 29})
    public void testUpdateBoardWithNegativeInput(int param) {
        //GIVEN
        Board board = new Board();
        Player player = new Player();
        player.setSign("O");
        int el = param;

        //WHEN
        boolean result = board.updateBoard(player, el);

        //THEN
        assertFalse(result);
    }
}