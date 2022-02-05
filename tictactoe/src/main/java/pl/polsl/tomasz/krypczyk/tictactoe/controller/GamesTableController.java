package pl.polsl.tomasz.krypczyk.tictactoe.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.tomasz.krypczyk.tictactoe.model.GameTableRow;
import pl.polsl.tomasz.krypczyk.tictactoe.service.GamesTableService;

import java.util.ArrayList;

/**
 * Author: Tomasz Krypczyk
 * Game controler class as servlet
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/gamestable")
public class GamesTableController {

    /**
     * Games table service field
     */
    private final GamesTableService gamesTableService;

    /**
     * Method that sends list with table rows to web page
     * @param gamesTableService service for games list
     * @return response entity with games list
     */
    @GetMapping("/table")
    public ResponseEntity<ArrayList<GameTableRow>> getTableRows(GamesTableService gamesTableService){
        return ResponseEntity.ok(gamesTableService.createGamesList());
    }
}
