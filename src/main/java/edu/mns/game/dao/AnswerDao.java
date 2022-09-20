package edu.mns.game.dao;

import edu.mns.game.model.Answer;
import edu.mns.game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerDao extends JpaRepository<Answer,Integer> {

    List<Answer> findAllByCreatorId(Integer creator_id);
    List<Answer> findAllByQuestionId(Integer question_id);
    List<Answer> findAllByChainedQuestionId(Integer question_chained_id);

}
