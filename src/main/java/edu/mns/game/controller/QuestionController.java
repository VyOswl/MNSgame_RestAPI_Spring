package edu.mns.game.controller;

import edu.mns.game.dao.CreatorDao;
import edu.mns.game.dao.QuestionDao;
import edu.mns.game.model.Answer;
import edu.mns.game.model.Creator;
import edu.mns.game.model.Question;
import edu.mns.game.model.User;
import edu.mns.game.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/creator/question/{id}")
    public Question question(@PathVariable Integer id) {
        return this.questionDao.findById(id).orElse(null);
    }

    @GetMapping("/creator/questions/by-theme/{themeId}")
    public List<Question> findAllByThemeId( @PathVariable int themeId){
        return this.questionDao.findAllByThemeId(themeId);
    }

    @GetMapping("/creator/{creatorId}/questions")
    public List<Question> findAllByCreatorId( @PathVariable int creatorId){
        return this.questionDao.findAllByCreatorId(creatorId);
    }

    @GetMapping("/admin/list-question")
    public List<Question> listQuestions(){
        return this.questionDao.findAll();
    }


/*    @GetMapping("/questions/by-scenario-and-user/{scenarioId}/{userId}")
    public List<Question> findAllByUserIdAndScenarioId(@PathVariable int userId, @PathVariable int scenarioId){
        return this.questionDao.findAllByUserIdAndScenarioId(userId,scenarioId);
    }*/

    @PostMapping("/creator/new-question")
    public String createQuestion(@RequestBody Question question, @RequestHeader("Authorization") String jwt){
        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
        Optional<Creator> creator = creatorDao.findById(idUtilisateurConnecte);

        question.setCreator(creator.get());
        this.questionDao.save(question);

        return "Question créée avec succès !";
    }

    ////////  A TESTER :  ne fonctionne pas : lier par une clé étrangère avec answer/////////
    @DeleteMapping("/creator/question/delete/{id}")
    public String deleteQuestion(
            @PathVariable int id,
            @RequestHeader("Authorization") String jwt) {
        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
        String droits = (String) jwtUtils.getTokenBody(token).get("droits");
            Optional<Question> question = questionDao.findById(id);

        Creator creator = question.get().getCreator();
        System.out.println("IDcréateur de la question : "+creator.getId());
        System.out.println("IDutilisateur voulant U/D la question: "+idUtilisateurConnecte);
        if(droits.contains("creator") && idUtilisateurConnecte == creator.getId()) {
            if (question.isPresent()) {
                this.questionDao.deleteById(id);
                return "OK : Question supprimée";
                //                return ResponseEntity.ok(question.get());
            } else {
                return "Question non disponible";
                //return ResponseEntity.noContent().build();

            }
        }else{
            return "pas d'autorisation ou pas créateur de la question";
            //return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/creator/question/update/{id}")
    public String updateQuestion(
             @RequestBody Question question,
            @PathVariable Integer id,
             @RequestHeader("Authorization") String jwt){

        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
        String droits = (String) jwtUtils.getTokenBody(token).get("droits");

        Optional<Question> questionAmodifier = questionDao.findById(id);

        Creator creator = questionAmodifier.get().getCreator();
        System.out.println("IDcréateur de la question : "+creator.getId());
        System.out.println("IDutilisateur voulant U/D la question: "+idUtilisateurConnecte);
        if(droits.contains("ROLE_CREATOR") && idUtilisateurConnecte == creator.getId()) {
            if (questionAmodifier.isPresent()) {
                questionAmodifier.get().setPoints(question.getPoints());
                questionAmodifier.get().setContent(question.getContent());
                questionAmodifier.get().setTheme(question.getTheme());
                //return ResponseEntity.ok(questionAmodifier.get());
                this.questionDao.save(questionAmodifier.get());
                return "Question modifiée avec succès !";
            }
        }else {
            return "pas d'autorisation ou pas créateur de la question";

        }
            //return ResponseEntity.noContent().build();
        return "Erreur rencontrée lors de la modificaton de la question n° "+id;
    }


/*    @GetMapping("/user/points-by-scenario/{userId}/{scenarioId}")
    public Integer findAllPointsByUserIdAndScenarioId(@Param("userId") int userId, @Param("scenarioId") int scenarioId){

        List<Integer> result = this.questionDao.findAllPointsByUserIdAndScenarioId(userId, scenarioId);
        Integer resultat = 0;

        for ( Integer ligne : result){
            resultat += ligne;
        }
        return resultat;
    }*/

}
