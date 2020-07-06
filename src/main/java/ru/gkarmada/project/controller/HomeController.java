package ru.gkarmada.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    // got to index
    @RequestMapping(value={"/", "/index"})
    public String home(Model model){
        return "index";
    }

}
