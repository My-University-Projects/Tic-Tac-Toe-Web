package pl.polsl.tomasz.krypczyk.tictactoe.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * Test of addPosition method
     * @param param positive input
     */
    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3, 4, 5, 6, 7, 8, 8})
    void testAddPosition(int param) {

        //GIVEN
        Player player = new Player();
        player.setPositions(new ArrayList<>());
        List<Integer> expResult = new ArrayList<>();

        //WHEN
        player.addPosition(param);
        expResult.add(param);

        //THEN
        assertEquals(expResult, player.getPositions());
    }
}