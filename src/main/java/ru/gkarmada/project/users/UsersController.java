package ru.gkarmada.project.users;

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
import ru.gkarmada.project.users.Users;
import ru.gkarmada.project.users.UsersRepository;
import ru.gkarmada.project.users.UsersService;

// controls CRUD table of employees
@Controller
public class UsersController {

  @Autowired
  private UsersService userservice;

  // injects objects from the constructor to the login controller
  private final UsersRepository userepo;

  UsersController(UsersRepository userepo) {
    this.userepo = userepo;
  }

  // get logger to log to the console
//  final Logger log = (Logger) LoggerFactory.getLogger(ProjectApplication.class);

  // injects methods from the service


  @RequestMapping("/users")
  public String UsersAdmin(Model model){
    List<Users> listUsers = userservice.listAll();
    //log.info("-------------------------------");
    //for (Users users : listUsers) {
     // log.info(users.toString());
    //}
   // log.info("XXXXXXXXXXXXXX");
    //log.info(String.valueOf(listUsers));
    //userservice.listAll().forEach(users -> log.info("{}", userservice));
    model.addAttribute("listUsers", listUsers);
    return "users";
  }
  // new employees
  @RequestMapping("/new_user")
  public String showNewUsersForm(Model model){
    Users user = new Users();
    model.addAttribute("user", user);
    return "new_user";
  }
  // save employee changes
  @RequestMapping(value = "/save_user", method = RequestMethod.POST)
  public String saveUser(@ModelAttribute("users") Users  user) {
    userservice.save(user);

    return "redirect:/employees";
  }
  // edit employees
  @RequestMapping("/edit_user/{userid}")
  public ModelAndView showEditEmployeePage(@PathVariable(name = "userid") Long userid) {
    ModelAndView mav = new ModelAndView("edit_user");
    Users user = userservice.get(userid);
    mav.addObject("user", user);
    return mav;
  }
  // delete the employees
  @RequestMapping("/delete_user/{userid}")
  public String deleteUser(@PathVariable(name = "userid") Long userid) {
    userservice.delete(userid);
    return "redirect:/users";
  }


}
