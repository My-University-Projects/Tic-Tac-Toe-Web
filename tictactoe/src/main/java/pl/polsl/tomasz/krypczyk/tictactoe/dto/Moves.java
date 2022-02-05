package pl.polsl.tomasz.krypczyk.tictactoe.dto;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="Moves")
@Table
public class Moves {

    @Id
    @SequenceGenerator(
            name = "moves_sequence",
            sequenceName = "moves_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "moves_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_uuid", referencedColumnName = "game_uuid")
    private GameInfo game;

    @Column(
            name = "first_player_moves",
            updatable = true,
            nullable = true,
            insertable = false

    )
    private String firstPlayerMoves;

    @Column(
            name = "second_player_moves",
            updatable = true,
            columnDefinition = "TEXT",
            nullable = true
    )
    private String secondPlayerMoves;

    public GameInfo getGame() {
        return game;
    }

    public void setGame(GameInfo game) {
        this.game = game;
    }


    public Moves() {

    }

    @Override
    public String toString() {
        return "Moves{" +
                "id=" + id +
                ", firstPlayerMoves='" + firstPlayerMoves + '\'' +
                ", secondPlayerMoves='" + secondPlayerMoves + '\'' +
                '}';
    }

    public Moves(GameInfo game, String firstPlayerMoves, String secondPlayerMoves) {
        this.game = game;
        this.firstPlayerMoves = firstPlayerMoves;
        this.secondPlayerMoves = secondPlayerMoves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstPlayerMoves() {
        return firstPlayerMoves;
    }

    public void setFirstPlayerMoves(String firstPlayerMoves) {
        this.firstPlayerMoves = firstPlayerMoves;
    }

    public String getSecondPlayerMoves() {
        return secondPlayerMoves;
    }

    public void setSecondPlayerMoves(String secondPlayerMoves) {
        this.secondPlayerMoves = secondPlayerMoves;
    }
}
