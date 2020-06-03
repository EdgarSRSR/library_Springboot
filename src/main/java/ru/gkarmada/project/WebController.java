package ru.gkarmada.project;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @Autowired
  private UserDAO userDAO;

  @GetMapping(path = "/")
  public String index() {
    return "external";
  }

  @GetMapping(path = "/users")
  public String customers(Principal principal, Model model) {
    addUsers();
    Iterable<User> customers = userDAO.findAll();
    model.addAttribute("user", customers);
    model.addAttribute("username", principal.getName());
    return "users";
  }

  // add customers for demonstration
  public void addUsers() {

    User user1 = new User();
    user1.setEmail("example@gmail.com");
    user1.setName("employeeA");
    user1.setServiceRendered("Important services");
    userDAO.save(user1);


  }

}
