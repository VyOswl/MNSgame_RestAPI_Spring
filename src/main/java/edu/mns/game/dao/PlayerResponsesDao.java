package edu.mns.game.dao;

import edu.mns.game.model.KeyPlayerResponses;
import edu.mns.game.model.PlayerResponses;
import edu.mns.game.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerResponsesDao extends JpaRepository<PlayerResponses, KeyPlayerResponses> {

    //@Query(value="FROM Question q INNER PlayerResponses pr WHERE pr.user.id = :userId AND pr.scenario.id = :scenarioId")
    //@Query(value="SELECT pr.question_id, pr.answer_id, an.is_correct  FROM  player_responses pr WHERE pr.user_id = 3 AND pr.scenario_id = 2")

    /// type de retour à revoir !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //@Query(value=" SELECT  pr.question_id, pr.answer_id, an.is_correct FROM question q INNER JOIN player_responses pr INNER JOIN answer an " +
            //"WHERE an.is_correct=1 AND an.id=pr.answer_id AND pr.user_id = :userId AND pr.scenario_id = :scenarioId ", nativeQuery = true)
            //    @Query(value="SELECT q.id, q.content, q.points, q.answer_id, q.theme_id FROM question q" +
    @Query(value="SELECT q.id, q.content, q.points, q.theme_id FROM question q " +
            "INNER JOIN  player_responses pr " +
            "INNER JOIN answer an ON q.answer_id = an.id AND  an.id=pr.answer_id AND  an.is_correct=1 "+
            "WHERE pr.user_id = :userId AND pr.scenario_id = :scenarioId ", nativeQuery = true)
    List<Map<String,Object>> findAllByUserIdAndScenarioId(@Param("userId") Integer userId, @Param("scenarioId") Integer scenarioId);
    /// type de retour à revoir !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Query(value="SELECT q.points FROM  question q " +
            "INNER JOIN answer an ON q.answer_id = an.id " +
            "INNER JOIN player_responses pr ON an.id=pr.answer_id "+
            "WHERE an.is_correct= 1 AND pr.user_id = :userId AND pr.scenario_id = :scenarioId  ", nativeQuery = true)
    List<Integer> findAllPointsByUserIdAndScenarioId(@Param("userId") int userId, @Param("scenarioId") int scenarioId);






    //requête testée , juste : donne les réponses correct d'un joueur en fonction du scenario jouée :
    // "SELECT pr.question_id, pr.answer_id, an.is_correct FROM  player_responses pr
    // INNER JOIN answer an WHERE pr.user_id = 3 AND pr.scenario_id = 2 AND an.is_correct=0 AND an.id=pr.answer_id"
    //select points from question where question.id = 1;
}
