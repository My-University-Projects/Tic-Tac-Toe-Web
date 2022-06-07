package pl.polsl.tomasz.krypczyk.tictactoe.storage;

import pl.polsl.tomasz.krypczyk.tictactoe.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Singleton class that contains information about all games
 */
public class GameStorage {

    /**
     * field contains games
     */
    private static Map<String, Game> games;

    /**
     * field that is instance of the game storage object
     */
    private static GameStorage instance;

    /**
     * Constructor
     */
    private GameStorage() {
        games = new HashMap<>();
    }

    /**
     * getter of the game storage instance
     * @return instance of the game storage
     */
    public static synchronized GameStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GameStorage();
        }
        return instance;
    }

    /**
     * getter of the games map
     * @return
     */
    public Map<String, Game> getGames() {
        return games;
    }

    /**
     * setter of the games map
     * @param game
     */
    public void setGame(Game game) {
        games.put(game.getGameId(), game);
    }
}
