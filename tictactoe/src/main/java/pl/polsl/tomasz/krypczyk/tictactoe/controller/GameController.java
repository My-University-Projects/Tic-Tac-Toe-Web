package pl.polsl.tomasz.krypczyk.tictactoe.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.ConnectRequest;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.GameNotFoundException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.InvalidGameIdException;
import pl.polsl.tomasz.krypczyk.tictactoe.exception.Player2ExistsException;
import pl.polsl.tomasz.krypczyk.tictactoe.model.Game;
import pl.polsl.tomasz.krypczyk.tictactoe.model.Move;
import pl.polsl.tomasz.krypczyk.tictactoe.model.Player;
import pl.polsl.tomasz.krypczyk.tictactoe.service.GameService;


/**
 * Game controler class as servlet
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    /**
     * game service field
     */
    private final GameService gameService;
    /**
     * smt field for web sockets
     */
    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Method that creates a new game
     * @param player1 player that started a new game
     * @return new game entity to server as JSON
     */
    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player1) {
        return ResponseEntity.ok(gameService.createGame(player1));
    }

    /**
     * Method that connects second player to the game
     * @param request information about player and id of the game
     * @return game
     * @throws Player2ExistsException
     * @throws InvalidGameIdException
     */
    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws Player2ExistsException, InvalidGameIdException {
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayerName(), request.getGameId()));
    }

    /**
     * Method that connects second player to random created game
     * @param request information about player and id of the game
     * @return game
     * @throws Player2ExistsException
     * @throws InvalidGameIdException
     * @throws GameNotFoundException
     */
    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectToRandom(@RequestBody ConnectRequest request) throws Player2ExistsException, InvalidGameIdException, GameNotFoundException {
        return ResponseEntity.ok(gameService.connectToRandomGame(request.getPlayerName()));
    }

    /**
     * Method that is responsible for monitoring players moves
     * @param request information about player sign, taken position and game id
     * @return game
     * @throws GameNotFoundException
     */
    @PostMapping("/move")
    public ResponseEntity<Game> move(@RequestBody Move request) throws GameNotFoundException {

        Game game = gameService.move(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
        return ResponseEntity.ok(game);

    }


}
