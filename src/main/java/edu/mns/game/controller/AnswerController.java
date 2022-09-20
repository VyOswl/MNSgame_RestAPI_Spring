package edu.mns.game.controller;

import edu.mns.game.dao.AnswerDao;
import edu.mns.game.dao.CreatorDao;
import edu.mns.game.model.Answer;
import edu.mns.game.model.Creator;
import edu.mns.game.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class AnswerController {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/creator/answer/{id}")
    public Answer answer(@PathVariable Integer id) {
        return this.answerDao.findById(id).orElse(null);
    }

    @GetMapping("/creator/{creatorId}/answers")
    public List<Answer> findByCreatorId( @PathVariable Integer creatorId){
        return this.answerDao.findAllByCreatorId(creatorId);
    }

//    @GetMapping("/answers/question/{questionId}")
//    public List<Answer> findAllByQuestionId( @PathVariable Integer questionId){
//        return this.answerDao.findAllByQuestionId(questionId);
//    }

//    @GetMapping("/answers/chaineQuestion/{chainedQuestionId}")
//    public List<Answer> findAllByChainedQuestionId( @PathVariable Integer chainedQuestionId){
//        return this.answerDao.findAllByChainedQuestionId(chainedQuestionId);
//    }

    @PostMapping("/creator/new-answer")
    public String createAnswer(@RequestBody Answer answer, @RequestHeader("Authorization") String jwt){
        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
        Optional<Creator> creator = creatorDao.findById(idUtilisateurConnecte);

        answer.setCreator(creator.get());

        this.answerDao.save(answer);
        return "réponse créée avec succès";
    }

    @DeleteMapping("/creator/answer/delete/{id}")
        public ResponseEntity<Answer> deleteAnswer(@PathVariable int id) {
        Optional<Answer> answer = answerDao.findById(id);
        if (answer.isPresent()) {
            this.answerDao.deleteById(id);
            return ResponseEntity.ok(answer.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//    @DeleteMapping("/answer/delete-by-questionId/{id}")
//    public String deleteAnswers(@PathVariable int questionId) {
//        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
//        answers.forEach(answer ->
//                this.answerDao.deleteById(answer.getId()));
//
//            return "OK : réponses supprimés";
//    }

    ////////  MODIFIER REPONSE :
    /*@PostMapping("/question")
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestHeader("Authorization") String jwt){
        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
        String droits = (String) jwtUtils.getTokenBody(token).get("droits");

        if(droits.contains("ROLE_ADMIN") || idUtilisateurConnecte == user.getId()){
            Optional<User> ancienUtilisateur = userDao.findById(user.getId());

            if(ancienUtilisateur.isPresent()){
                ancienUtilisateur.get().setFirstname(user.getFirstname());
                ancienUtilisateur.get().setLastname(user.getLastname());
                this.userDao.save(ancienUtilisateur.get());

                return ResponseEntity.ok(ancienUtilisateur.get());
            }

            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }*/

}
