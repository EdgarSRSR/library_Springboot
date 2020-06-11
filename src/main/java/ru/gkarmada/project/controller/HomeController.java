package ru.gkarmada.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    // got to index
    @RequestMapping("/")
    public String home(Model model){
        return "index";
    }

    // go to library
    /*@RequestMapping( value="/library", method = RequestMethod.GET)
    public String getLibrary(){
        return "library";
    }

    // go to users
    @RequestMapping( value="/users", method = RequestMethod.GET)
    public String getUsers(){
        return "users";
    }*/
}
