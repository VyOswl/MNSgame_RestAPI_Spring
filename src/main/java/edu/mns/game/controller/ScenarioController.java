package edu.mns.game.controller;

import edu.mns.game.dao.ScenarioDao;
import edu.mns.game.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class ScenarioController {

    @Autowired
    private ScenarioDao scenarioDao;

    @GetMapping("/creator/scenario/{id}")
    public Scenario scenario(@PathVariable Integer id) {
        return this.scenarioDao.findById(id).orElse(null);
    }

    @GetMapping("/creator/scenario/by-name/{scenarioName}")
    public Scenario findByScenarioName( @PathVariable String scenarioName){
        return this.scenarioDao.findByName(scenarioName).orElse(null);
    }

   @GetMapping("/creator/scenario/published/{isPublished}")
    public List<Scenario> findAllByIsPublished(@PathVariable Boolean isPublished){
        return this.scenarioDao.findAllByIsPublished(isPublished);
    }

    @GetMapping("/creator/scenarios/by-user/{userId}")
    public List<Scenario> findAllByParticipant(@PathVariable int userId){
        return this.scenarioDao.findAllByParticipant(userId).orElse(null);
    }

    @GetMapping("/creator/list-scenario")
    public List<Scenario> listScenario(){
        return this.scenarioDao.findAll();
    }
}
