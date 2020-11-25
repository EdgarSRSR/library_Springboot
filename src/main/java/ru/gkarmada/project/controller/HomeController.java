package ru.gkarmada.project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.gkarmada.project.user.User;

@Controller
public class HomeController {


    // got to index
    @RequestMapping(value = {"/", "/index"})
    public String home(Model model) {
        return "index";
    }

    // log out
    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "logout";
    }





}
