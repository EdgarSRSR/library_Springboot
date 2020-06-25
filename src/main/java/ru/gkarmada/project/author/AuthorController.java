package ru.gkarmada.project.author;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.book.Book;
import ru.gkarmada.project.book.BookRepository;
import ru.gkarmada.project.book.BookService;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Controller
public class AuthorController {

  // injects author services
  @Autowired
  private AuthorService authorservice;

  private final AuthorRepository authrepo;

  AuthorController(AuthorRepository authrepo) {
    this.authrepo = authrepo;
  }

  // get logger to log to the console
  final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());


  //Method to add new Author
  @RequestMapping("library/new_author")
  public String showNewAuthorForm(Model model){
    Author author = new Author();
    model.addAttribute("author", author);
    return "library/new_author";
  }

  //method that saves changes to author

  @PostMapping(value = "/author_save")
  public String saveAuthor(@ModelAttribute("author") Author author)
      throws BadResourceException, ResourceAlreadyExistsException {
    authorservice.saveAuthor(author);
    return "redirect:/library/new";
  }


  // method to delete a book
  @RequestMapping("/deleteauthor/{authorid}")
  public String deleteAuthor(@PathVariable(name = "authorid") Long authorid) {
    authorservice.deleteAuthor(authorid);
    return "redirect:/library";
  }

}
