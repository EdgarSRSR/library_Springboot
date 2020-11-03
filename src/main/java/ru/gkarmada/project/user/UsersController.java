package ru.gkarmada.project.user;

// This class is in charge of implementing changes in  the Employees table located in the admin page

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// controls CRUD table of employees
@Controller
public class UsersController {

  @Autowired
  private UsersService usersservice;

  // injects objects from the constructor to the login controller
  private final UsersRepository userepo;

  UsersController(UsersRepository userepo) {
    this.userepo = userepo;
  }

  // get logger to log to the console
  //final Logger log = (Logger) LoggerFactory.getLogger(ProjectApplication.class.getName());

  // injects methods from the service


  @RequestMapping("/users")
  public String UsersAdmin(Model model){
    List<User> listUsers = usersservice.listAll();
    /*log.info("-------------------------------");
    for (User users : listUsers) {
      log.info(users.toString());
    }
   log.info("XXXXXXXXXXXXXX");
    log.info(String.valueOf(listUsers));
    //usersservice.listAll().forEach(users -> log.info("{}", usersservice));*/
    model.addAttribute("listUsers", listUsers);
    return "users/users";
  }


  // new employees
  @RequestMapping("/new_user")
  public String showNewUsersForm(Model model){
    User user = new User();
    model.addAttribute("user", user);
    return "new_user";
  }
  // save employee changes
  @RequestMapping(value = "/save_user", method = RequestMethod.POST)
  public String saveUser(@ModelAttribute("users") User user) {
    usersservice.save(user);

    return "redirect:/users";
  }
  // edit employees
  @RequestMapping("/edit_user/{userid}")
  public ModelAndView showEditEmployeePage(@PathVariable(name = "userid") Long userid) {
    ModelAndView mav = new ModelAndView("edit_user");
    User user = usersservice.get(userid);
    mav.addObject("user", user);
    return mav;
  }
  // delete the employees
  @RequestMapping("/delete_user/{userid}")
  public String deleteUser(@PathVariable(name = "userid") Long userid) {
    usersservice.delete(userid);
    return "redirect:/users";
  }


}
