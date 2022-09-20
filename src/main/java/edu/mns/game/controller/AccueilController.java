package edu.mns.game.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AccueilController {
    @GetMapping("/")
    public String resourcePourToutLeMonde() {
        return "Hello !";
    }
    @GetMapping("/player/hello")
    public String resourcePourJoueur() {
        return "Hello player";
    }
    @GetMapping("/creator/hello")
    public String resourcePourCreateurDeJeu() {
        return "Hello creator";
    }
}
