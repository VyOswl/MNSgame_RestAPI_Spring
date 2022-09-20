package edu.mns.game.dao;

import edu.mns.game.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScenarioDao extends JpaRepository<Scenario,Integer> {
    Optional<Scenario> findByName(String scenarioName);
    List<Scenario> findAllByIsPublished(Boolean is_published);

    @Query(value="FROM Scenario s INNER JOIN s.listParticipants p WHERE p.id = :userId")
    Optional<List<Scenario>> findAllByParticipant(@Param("userId") int userId);

    //par is_publish
}
