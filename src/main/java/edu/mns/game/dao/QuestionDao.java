package edu.mns.game.dao;

import edu.mns.game.model.Answer;
import edu.mns.game.model.Question;
import edu.mns.game.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer > {
    List<Question> findAllByThemeId(int themeId);
    List<Question> findAllByCreatorId(int creator_id);
/*    @Query(value="FROM question q INNER JOIN answer an ON q.answer_id = an.id "+
            "INNER JOIN player_responses pr ON  an.id=pr.answer_id "+
            "WHERE an.is_correct= 1 AND pr.user_id = :userId AND pr.scenario_id = :scenarioId  ")
    List<Integer> findAllPointsByUserIdAndScenarioId(@Param("userId") int userId, @Param("scenarioId") int scenarioId);*/

}
