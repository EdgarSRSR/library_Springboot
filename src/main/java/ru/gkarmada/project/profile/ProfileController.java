package ru.gkarmada.project.profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

  // log out
  @GetMapping(path = "/profile")
  public String logout(HttpServletRequest request) throws ServletException {
    request.logout();
    return "/userprofile/profile";
  }

}
