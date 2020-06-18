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

  private final BookRepository bookrepo;

  // uri change
  private final int ROW_PER_PAGE = 5;

  BookController(BookRepository bookrepo) {
    this.bookrepo = bookrepo;
  }

  // get logger to log to the console
  final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

  // Method that fills the the Table of the Book for the admins
  @GetMapping("/library")
  public String viewBooks(Model model) {

    System.out.println("XXXXXXXXXXXXXX");
    log.info("XXXXXXXXXXXXXX");
    List<Book> listBooks = bookservice.listAll();
    log.info("-------------------------------");
    for (Book book : bookrepo.findAll()) {
      log.info(book.toString());
    }
    log.info("XXXXXXXXXXXXXX");
    log.info(String.valueOf(listBooks));
    bookservice.listAll().forEach(book -> log.info("{}", bookservice));
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
  @RequestMapping("/rent/{bookid}")
  public ModelAndView showRentBookPage(@PathVariable(name = "bookid") Long bookid) {
    ModelAndView mav = new ModelAndView("rent_book");
    Book book = bookservice.get(bookid);
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
    log.info("---------------rent books----------------");
    //log.info(String.valueOf(book.bookid));
    mav.addObject("book", book);

    return mav;
  }

  // method to delete a book

  @RequestMapping("/delete/{bookid}")
  public String deleteBook(@PathVariable(name = "bookid") Long bookid) {
    bookservice.delete(bookid);
    return "redirect:/library/list";
  }

  //////// URI RESTRUCTURE //////////


  @GetMapping(value = "/library", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Book>> findAll(
      @RequestParam(value="page", defaultValue="1") int pageNumber,
      @RequestParam(required=false) String title) {
    if (StringUtils.isEmpty(title)) {
      return ResponseEntity.ok(bookservice.findAll(pageNumber, ROW_PER_PAGE));
    }
    else {
      return ResponseEntity.ok(bookservice.findAllByTitle(title, pageNumber, ROW_PER_PAGE));
    }
  }

  // Find Book By Id
  @GetMapping(value = "/library/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Book> findBookById(@PathVariable long BookId) {
    try {
      Book book = bookservice.findById(BookId);
      return ResponseEntity.ok(book);  // return 200, with json body
    } catch ( ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
    }
  }

  @PostMapping(value = "/library")
  public ResponseEntity<Book> addBook( @RequestBody Book book)
      throws URISyntaxException {
    try {
      Book newBook = bookservice.save(book);
      return ResponseEntity.created(new URI("/api/library/" + newBook.getBookid()))
          .body(book);
    } catch (ResourceAlreadyExistsException ex) {
      // log exception first, then return Conflict (409)
      log.error(ex.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (BadResourceException ex) {
      // log exception first, then return Bad Request (400)
      log.error(ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /*
  @PutMapping(value = "/library/{bookid}")
  public ResponseEntity<Book> updateBook( @RequestBody Book book,
      @PathVariable long bookid) {
    try {
      book.setBookid(bookid);
      bookservice.update(book);
      return ResponseEntity.ok().build();
    } catch (ResourceNotFoundException ex) {
      // log exception first, then return Not Found (404)
      log.error(ex.getMessage());
      return ResponseEntity.notFound().build();
    } catch (BadResourceException ex) {
      // log exception first, then return Bad Request (400)
      log.error(ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }*/


  @DeleteMapping(path="/library/{bookid}")
  public ResponseEntity<Void> deleteBookById(@PathVariable long bookid) {
    try {
      bookservice.deleteById(bookid);
      return ResponseEntity.ok().build();
    } catch (ResourceNotFoundException ex) {
      log.error(ex.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
