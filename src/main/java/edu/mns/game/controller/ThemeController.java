package edu.mns.game.controller;

import edu.mns.game.dao.ThemeDao;
import edu.mns.game.dao.UserDao;
import edu.mns.game.model.Creator;
import edu.mns.game.model.Question;
import edu.mns.game.model.Theme;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
public class ThemeController {
    @Autowired
    private ThemeDao themeDao;

    @GetMapping("/creator/list-theme")
    public List<Theme> listThemes(){
        return this.themeDao.findAll();
    }

    @PostMapping("/creator/new-theme")
    public String createTheme(@RequestBody Theme theme){
//        String token = jwt.substring(7);
//        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");
//        Optional<Creator> creator = creatorDao.findById(idUtilisateurConnecte);
//
//        question.setCreator(creator.get());
        this.themeDao.save(theme);

        return "Theme créé avec succès !";
    }
}
