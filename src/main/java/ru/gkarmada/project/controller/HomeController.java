package ru.gkarmada.project.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gkarmada.project.ProjectApplication;

@Controller
public class HomeController {



    // got to index
    @RequestMapping(value = {"/", "/index"})
    public String home(Model model) {

        // get logger to log to the console
        final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
            .map(r -> r.getAuthority()).collect(Collectors.toSet());
        log.info(String.valueOf(roles));

        return "index";
    }

    // log out
    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "logout";
    }







}
