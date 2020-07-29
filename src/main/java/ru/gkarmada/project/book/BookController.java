package ru.gkarmada.project.book;

import java.util.HashSet;
import java.util.List;

import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.author.AuthorService;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;
import ru.gkarmada.project.genre.Genre;
import ru.gkarmada.project.genre.GenreService;
// Controls the creation of book table and table database


@Controller
@ControllerAdvice
//@RequestMapping("/api")
public class BookController {

    // injects book services
    @Autowired
    private BookService bookService;

    //
    @Autowired
    private AuthorService authorService;

    // genre service
    @Autowired
    private GenreService genreService;

    private final BookRepository bookRepository;

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // get logger to log to the console
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

    // Method that fills the the Table of the Book for the admins
    @GetMapping("/library")
    public String viewBooks(Model model, @SortDefault("title") Pageable pageable) {

        //
        List<Author> authors= authorService.listAll();
        List<Genre> listGenres = genreService.listAll();
        List<Book> listBooks = bookService.listAll();
        for (Book book : bookRepository.findAll()) {
            log.info(book.toString());
        }
        //

        model.addAttribute("page", bookService.find(pageable));
        model.addAttribute("listGenres", listGenres);
        model.addAttribute("listBooks", listBooks);
        return "library/list";
    }


    //Method to add new Book
    @RequestMapping("library/new")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        List<Genre> genres= genreService.listAll();
        List<Author> authors= authorService.listAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "library/new";
    }

    //method that saves changes to books
    @PostMapping(value = "/save")
    public String saveBook(Book book)
            throws BadResourceException, ResourceAlreadyExistsException {
        bookService.save(book);
        return "redirect:/library";
    }

    //method to implement rent or return book
    @PostMapping(value = "/rentreturn")
    public String rentreturnBook(@ModelAttribute("book") Book book)
            throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        bookService.update(book);
        return "redirect:/library";
    }

    // Method to create page for renting or returning a book
    @RequestMapping("/library/rent/{bookid}")
    public ModelAndView showRentBookPage(@PathVariable(name = "bookid") Long bookid) {
        ModelAndView mav = new ModelAndView("/library/rent");
        Book book = bookService.get(bookid);
        final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
        log.info("---------------rent books----------------");
        mav.addObject("book", book);

        return mav;
    }

    //add author to book
    @RequestMapping(value="/library/fragments/authorsModal", method= RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute Book book,
        BindingResult result,
        @RequestParam("authors") String authors,
        Model model,
        SessionStatus status) throws BadResourceException, ResourceAlreadyExistsException {
        if (result.hasErrors()){
            return "library/new";
        }
        else {

            return "redirect:/library/new";

        }
    }

    // method to delete a book
    @RequestMapping("/delete/{bookid}")
    public String deleteBook(@PathVariable(name = "bookid") Long bookid) {
        bookService.delete(bookid);
        return "redirect:/library";
    }

/*

     // Method to create page for adding authors to book
    @RequestMapping("/library/addAuthors/{id}")
    public ModelAndView showAddAuthorsPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("library/addAuthors");
        Book book = bookService.getBook(id);
        final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
        log.info("---------------add authors----------------");
        mav.addObject("book", book);
        return mav;
    }


    //method to update
    @PostMapping(value = "library/addGenres")
    public String addGenres(@ModelAttribute("author") Author author)
        throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        List<Genre> genres= genreService.listAll();
        return "redirect:/author/list";
    }*/



}
