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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;
import ru.gkarmada.project.genre.Genre;

@Controller
public class AuthorController {

    // injects author services
    @Autowired
    private AuthorService authorservice;

    private final AuthorRepository authorRepository;

    AuthorController(AuthorRepository authorRepository1) {
        this.authorRepository = authorRepository1;
    }

    // get logger to log to the console
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

    // Method that fills the the Table of the Author for the admins
    @GetMapping("/author/list")
    public String viewAuthors(Model model) {
        //
        //List<Author> listAuthors = authorservice.listAll();
        List<Author> listAuthors = authorservice.listAll();
        for (Author author : authorRepository.findAll()) {
            log.info(author.toString());
        }
        //
        //model.addAttribute("authors", book.getAuthors());
        model.addAttribute("listAuthors", listAuthors);
        return "author/list";
    }


    //Method to add new Author
    @RequestMapping("author/new")
    public String showNewAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "author/new";
    }

    //method that saves changes to author

    @PostMapping(value = "/author_save")
    public String saveAuthor(@ModelAttribute("author") Author author)
            throws BadResourceException, ResourceAlreadyExistsException {
        authorservice.save(author);
        for (Author author1 : authorRepository.findAll()) {
            log.info(author.toString());
        }
        return "redirect:/author/list";
    }

    // method to delete an author
    @RequestMapping("/delete_author/{id}")
    public String deleteAuthor(@PathVariable(name = "id") Long id, Model model) {

        try {
        authorservice.deleteAuthor(id);
        } catch (Exception ex) {
            model.addAttribute("error", ex);
        }
        return "redirect:/author/list";
    }

    //method to update
    @PostMapping(value = "author/update")
    public String updateAuthor(@ModelAttribute("author") Author author)
            throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        authorservice.update(author);
        return "redirect:/author/list";
    }

    // Method to create page for updating author
    @RequestMapping("/author/update/{id}")
    public ModelAndView showUpdateAuthorPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("/author/update");
        Author author = authorservice.getAuthor(id);
        final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
        log.info("---------------update author----------------");
        mav.addObject("author", author);

        return mav;
    }


    @RequestMapping("/author/{id}")
    @ResponseBody
    public Author findAuthor(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return authorservice.getAuthor(id);
    }

}
