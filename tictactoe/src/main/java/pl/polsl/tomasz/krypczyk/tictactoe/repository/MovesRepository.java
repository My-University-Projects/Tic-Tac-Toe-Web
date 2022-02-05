package pl.polsl.tomasz.krypczyk.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.Moves;

@Repository
public interface MovesRepository extends JpaRepository<Moves, Integer> {

    @Transactional
    @Modifying
    @Query("update Moves m set m.firstPlayerMoves = :moves WHERE m.game.gameUUID LIKE :game_uuid")
    void updateFirstPlayerMoves(@Param("game_uuid") String game_uuid, @Param("moves") String moves);

    @Transactional
    @Modifying
    @Query("update Moves m set m.secondPlayerMoves = :moves WHERE m.game.gameUUID LIKE :game_uuid")
    void updateSecondPlayerMoves(@Param("game_uuid") String game_uuid, @Param("moves") String moves);
}
