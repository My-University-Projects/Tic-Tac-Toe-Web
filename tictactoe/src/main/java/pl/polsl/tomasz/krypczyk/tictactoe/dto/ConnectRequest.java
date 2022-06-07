package pl.polsl.tomasz.krypczyk.tictactoe.dto;

import lombok.Data;

/**
 * Class necessary for connect players to game
 */
@Data
public class ConnectRequest {
    private String playerName;
    private String gameId;
}
