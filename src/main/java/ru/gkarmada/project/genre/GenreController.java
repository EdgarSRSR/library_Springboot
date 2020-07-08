package ru.gkarmada.project.genre;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Controller
public class GenreController {
    // injects author services
    @Autowired
    private GenreService genreService;

    private final GenreRepository genreRepository;

    GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // get logger to log to the console
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

    // Method that fills the the Table of the Author for the admins
    @GetMapping("genre/list")
    public String viewGenre(Model model) {
        List<Genre> listGenres = genreService.listAll();
        for (Genre genre : genreRepository.findAll()) {
            log.info(genre.toString());
        }
        model.addAttribute("listGenres", listGenres);
        return "genre/list";
    }

    //Method to add new a genre
    @RequestMapping("genre/new")
    public String showNewGenreForm(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "genre/new";
    }

    //method that saves changes to author

    @PostMapping(value = "genre/save")
    public String saveGenre(@ModelAttribute("genre") Genre genre)
            throws BadResourceException, ResourceAlreadyExistsException {
        genreService.save(genre);
        for (Genre genre1 : genreRepository.findAll()) {
            log.info(genre.toString());
        }
        return "redirect:/genre/list";
    }


    // method to delete a genre
    @RequestMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable(name = "id") Long genreId) {
        genreService.delete(genreId);
        return "redirect:/genre/list";
    }

    //method to update
    @PostMapping(value = {"/genre/update"})
    public String updateGenre(@ModelAttribute("genre") Genre genre)
            throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        genreService.update(genre);
        return "redirect:/genre/list";
    }

    // Method to create page for updating a genre
    @RequestMapping("/genre/edit/{id}")
    public ModelAndView showUpdateGenrePage(@PathVariable(name = "id") Long genreId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("/genre/edit");
        Genre genre = genreService.get(genreId);
//        final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
//        log.info("---------------update author----------------");
        mav.addObject("genre", genre);
        return mav;
    }

    @RequestMapping("/genre/{id}")
    @ResponseBody
    public Genre findGenre(@PathVariable("id") Long genreId) throws ResourceNotFoundException {
        return genreService.get(genreId);
    }

}
