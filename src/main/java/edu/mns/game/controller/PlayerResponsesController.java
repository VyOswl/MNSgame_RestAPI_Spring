package edu.mns.game.controller;

import edu.mns.game.dao.PlayerResponsesDao;
import edu.mns.game.model.Question;
import edu.mns.game.model.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PlayerResponsesController {
    @Autowired
    private PlayerResponsesDao playerResponsesDao;

    @GetMapping("/user/{userId}/correct-answers/{scenarioId}")
        public List<Question> findAllByUserIdAndScenarioId(@PathVariable("userId") Integer userId, @PathVariable("scenarioId") Integer scenarioId){
        //return this.playerResponsesDao.findAllByUserIdAndScenarioId(userId, scenarioId);
        // transformer l'objet du retour en int, string, ...

        List<Map<String,Object>> result = this.playerResponsesDao.findAllByUserIdAndScenarioId(userId, scenarioId);
        List<Question> resultat = new ArrayList<>();

        for (Map<String,Object> ligne : result){
            Question question = new Question();
            Theme theme = new Theme();
            theme.setName((String) ligne.get("theme"));
            question.setId((Integer)ligne.get("id"));
            question.setContent((String) ligne.get("content"));
            question.setPoints((Float) ligne.get("points"));

            question.setTheme(theme);
            resultat.add(question);
        }
        return resultat;
    }

    @GetMapping("/user/{userId}/points-by-scenario/{scenarioId}")
    public Integer findAllPointsByUserIdAndScenarioId(@PathVariable("userId") int userId, @PathVariable("scenarioId") int scenarioId){

        List<Integer> result = this.playerResponsesDao.findAllPointsByUserIdAndScenarioId(userId, scenarioId);
        Integer resultat = 0;

        for ( Integer ligne : result){
            resultat += ligne;
        }
        return resultat;
    }
}
