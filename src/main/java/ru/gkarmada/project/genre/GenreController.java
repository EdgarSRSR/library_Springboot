package ru.gkarmada.project.genre;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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


    //method that saves changes to genre
    @PostMapping(value = {"genre/save", "/genre"})
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
    public String deleteGenre(@PathVariable long id, Model model, RedirectAttributes redirectAttrs) {

        try {
            genreService.deleteGenre(id);
            redirectAttrs.addFlashAttribute("message", "genre was deleted");
            //return "redirect:/genre/list";
        } catch (Exception ex) {
            model.addAttribute("error", ex);
            redirectAttrs.addFlashAttribute("warning", "A genre with an assigned author can't be deleted");
        }
        return "redirect:/genre/list";

    }
    //@RequestMapping(value={"/genre/delete"})
    /*public String deleteGenre(Model model, @ModelAttribute("genre") Genre genre) {

        try {
            //genreService.deleteGenre(id);
            genreService.delete(genre);
        } catch (Exception ex) {
            model.addAttribute("error", ex);
        }
            return "redirect:/genre/list";

    }*/

    //method to update
    @PostMapping(value = {"/genre/update"})
    public String updateGenre(@ModelAttribute("genre") Genre genre)
            throws BadResourceException, ResourceNotFoundException {
        genreService.update(genre);
        return "redirect:/genre/list";
    }

    // Method to create page for updating a genre
    @RequestMapping("/genre/edit/{id}")
    public ModelAndView showUpdateGenrePage(@PathVariable(name = "id") Long genreId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("/genre/edit");
        Genre genre = genreService.findById(genreId);
//        final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
//        log.info("---------------update author----------------");
        mav.addObject("genre", genre);
        return mav;
    }

    @RequestMapping("/genre/{id}")
    @ResponseBody
    public Genre findGenre(@PathVariable("id") Long genreId) throws ResourceNotFoundException {
        return genreService.findById(genreId);
    }



}
