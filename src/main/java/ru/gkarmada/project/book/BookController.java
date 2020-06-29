package ru.gkarmada.project.book;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.author.AuthorService;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;
// Controls the creation of book table and table database


@Controller
//@RequestMapping("/api")
public class BookController {

  // injects book services
  @Autowired
  private BookService bookservice;

  //
  @Autowired
  private AuthorService authorservice;

  private final BookRepository bookrepo;

  BookController(BookRepository bookrepo) {
    this.bookrepo = bookrepo;
  }

  // get logger to log to the console
  final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

  // Method that fills the the Table of the Book for the admins
  @GetMapping("/library")
  public String viewBooks(Model model) {


    //
    //List<Author> listAuthors = authorservice.listAll();
    List<Book> listBooks = bookservice.listAll();
    for (Book book : bookrepo.findAll()) {
      log.info(book.toString());
    }
    //
    //model.addAttribute("authors", book.getAuthors());
    model.addAttribute("listBooks", listBooks);
    return "library/list";
  }


  //Method to add new Book
  @RequestMapping("library/new")
  public String showNewBookForm(Model model){
    Book book = new Book();
    model.addAttribute("book", book);
    return "library/new";
  }

  //method that saves changes to books
  @PostMapping(value = "/save")
  public String saveBook(@ModelAttribute("book") Book book)
      throws BadResourceException, ResourceAlreadyExistsException {
    bookservice.save(book);
    return "redirect:/library";
  }

  //method to implement rent or return book
  @PostMapping(value = "/rentreturn")
  public String rentreturnBook(@ModelAttribute("book") Book book)
      throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
    bookservice.update(book);
    return "redirect:/library";
  }

  // Method to create page for renting or returning a book
  @RequestMapping("/library/rent/{bookid}")
  public ModelAndView showRentBookPage(@PathVariable(name = "bookid") Long bookid) {
    ModelAndView mav = new ModelAndView("/library/rent");
    Book book = bookservice.get(bookid);
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
    log.info("---------------rent books----------------");
    mav.addObject("book", book);

    return mav;
  }

  // method to delete a book
  @RequestMapping("/delete/{bookid}")
  public String deleteBook(@PathVariable(name = "bookid") Long bookid) {
    bookservice.delete(bookid);
    return "redirect:/library";
  }

}
