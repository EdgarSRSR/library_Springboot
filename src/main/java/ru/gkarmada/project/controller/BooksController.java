package ru.gkarmada.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.model.Books;
import ru.gkarmada.project.repository.BooksRepository;
import ru.gkarmada.project.service.BooksService;
// Controls the creation of book table and table database

@Controller
public class BooksController {

  // injects book services
  @Autowired
  private BooksService bookservice;

  private final BooksRepository bookrepo;

  BooksController(BooksRepository bookrepo) {
    this.bookrepo = bookrepo;
  }

  // Method that fills the the Table of the Books for the admins
  @RequestMapping("/library")
  public String viewBooks(Model model) {

    // get logger to log to the console
   final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

    System.out.println("XXXXXXXXXXXXXX");
    log.info("XXXXXXXXXXXXXX");
    List<Books> listBooks = bookservice.listAll();
    log.info("-------------------------------");
    for (Books book : bookrepo.findAll()) {
      log.info(book.toString());
    }
    //log.info("XXXXXXXXXXXXXX");
    //log.info(String.valueOf(listBooks));
    //bookservice.listAll().forEach(books -> log.info("{}", bookservice));
    model.addAttribute("listBooks", listBooks);
    return "library";
  }

  // Method that fills the the Table of the Books for the clients
 /* @RequestMapping("/books_client")
  public String viewBooksClients(Model model) {
    List<Books> listBooks = bookservice.listAll();
    model.addAttribute("listBooks", listBooks);
    return "books_client";
  }*/

  //Method to add new Books
  @RequestMapping("/new_book")
  public String showNewBookForm(Model model){
    Books book = new Books();
    model.addAttribute("book", book);
    return "new_book";
  }
  //method that saves changes to books
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String saveBook(@ModelAttribute("book") Books book) {
    bookservice.save(book);

    return "redirect:/books";
  }

  //method to implement rent or return book
  @RequestMapping(value = "/rentreturn", method = RequestMethod.POST)
  public String rentreturnBook(@ModelAttribute("book") Books book) {

    bookservice.save(book);
    return "redirect:/books";
  }
  //method to implement rent or return book client
  /*@RequestMapping(value = "/rentreturn_client", method = RequestMethod.POST)
  public String rentreturnBookClient(@ModelAttribute("book") Books book) {

    bookservice.save(book);
    return "redirect:/books_client";
  }*/

  // Method to create page for renting or returning a book
  @RequestMapping("/rent/{bookid}")
  public ModelAndView showRentBookPPage(@PathVariable(name = "bookid") Long bookid) {
    ModelAndView mav = new ModelAndView("rent_book");
    Books book = bookservice.get(bookid);
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
    log.info("---------------rent books----------------");
    //log.info(String.valueOf(book.bookid));
    mav.addObject("book", book);

    return mav;
  }
  // Method to create page for renting or returning a book for client
  /*@RequestMapping("/rent_client/{bookid}")
  public ModelAndView showRentBookClientPage(@PathVariable(name = "bookid") Long bookid) {
    ModelAndView mav = new ModelAndView("rentbook_client");
    Books book = bookservice.get(bookid);
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
    log.info("---------------rent books----------------");
    //log.info(String.valueOf(book.bookid));
    mav.addObject("book", book);

    return mav;
  }

   */
  // method to delete a book
  @RequestMapping("/delete/{bookid}")
  public String deleteBook(@PathVariable(name = "bookid") Long bookid) {
    bookservice.delete(bookid);
    return "redirect:/books";
  }


}
