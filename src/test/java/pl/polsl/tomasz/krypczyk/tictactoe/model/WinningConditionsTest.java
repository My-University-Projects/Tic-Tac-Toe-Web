package pl.polsl.tomasz.krypczyk.tictactoe.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WinningConditionsTest {

    /**
     * Test of checkWinConditions method, of class WinningConditions.
     */
    @Test
    public void testCheckWinConditionsWithPositiveWinConditions() {

        //GIVEN
        Player player = new Player();
        player.setPositions(new ArrayList<>());
        WinningConditions instance = new WinningConditions();
        player.addPosition(1);
        player.addPosition(2);
        player.addPosition(3);

        //WHEN
        boolean result = instance.checkWinConditions(player);

        //THEN
        assertTrue(result);
    }

    /**
     * Test of checkWinConditions method, of class WinningConditions.
     */
    @Test
    public void testCheckWinConditionsWithNegativeWinConditions() {

        //GIVEN
        Player player = new Player();
        player.setPositions(new ArrayList<>());
        WinningConditions instance = new WinningConditions();
        player.addPosition(4);
        player.addPosition(9);
        player.addPosition(3);

        //WHEN
        boolean result = instance.checkWinConditions(player);

        //THEN
        assertFalse(result);
    }
}