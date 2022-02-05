package pl.polsl.tomasz.krypczyk.tictactoe.dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name ="Game")
@Table
public class GameInfo {

    @Id
    @SequenceGenerator(
            name = "games_sequence",
            sequenceName = "games_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "games_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    @Column(
            name = "first_player_name",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstPlayerName;
    @Column(
            name = "second_player_name",
            updatable = false,
            nullable = true,
            columnDefinition = "TEXT"
    )

    private String secondPlayerName;

    @Column(
            name = "game_uuid",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String gameUUID;

    @Column(
            name = "status",
            updatable = true,
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String status;

    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "moves_id", referencedColumnName = "id")
   @OneToOne(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Moves moves;


    public GameInfo(String firstPlayerName, String secondPlayerName, String gameUUID, Moves moves) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.gameUUID = gameUUID;
        this.moves = moves;
    }

    public GameInfo() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Moves getMoves() {
        return moves;
    }

    public void setMoves(Moves moves) {
        this.moves = moves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public String getGameUUID() {
        return gameUUID;
    }

    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "id=" + id +
                ", firstPlayerName='" + firstPlayerName + '\'' +
                ", secondPlayerName='" + secondPlayerName + '\'' +
                ", gameUUID='" + gameUUID + '\'' +
                '}';
    }
}

