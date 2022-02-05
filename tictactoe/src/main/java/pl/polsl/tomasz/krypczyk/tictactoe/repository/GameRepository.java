package pl.polsl.tomasz.krypczyk.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.tomasz.krypczyk.tictactoe.dto.GameInfo;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameInfo, Integer> {

    @Transactional
    @Modifying
    @Query("update Game g set g.secondPlayerName = :name, g.status = :game_status WHERE g.gameUUID LIKE :gameUUID")
    void setSecondPlayerNameAndStatus(@Param("gameUUID") String gameUUID, @Param("name") String name, @Param("game_status") String status);

    @Transactional
    @Modifying
    @Query("update Game g set g.status = :game_status WHERE g.gameUUID LIKE :gameUUID")
    void updateFinishStatus(@Param("gameUUID") String gameUUID, @Param("game_status") String status);


    @Query(value = "Select * from Game", nativeQuery = true)
    List<GameInfo> getAll();

}
