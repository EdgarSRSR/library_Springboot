package ru.gkarmada.project.genre;

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
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.author.AuthorRepository;
import ru.gkarmada.project.author.AuthorService;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Controller
public class GenreController {
  // injects author services
  @Autowired
  private GenreService genreservice;

  private final GenreRepository genrerepo;

  GenreController(GenreRepository genrerepo) {
    this.genrerepo = genrerepo;
  }

  // get logger to log to the console
  final Logger log = LoggerFactory.getLogger(ProjectApplication.class.getName());

  // Method that fills the the Table of the Author for the admins
  @GetMapping("/genre/list")
  public String viewGenre(Model model) {
    List<Genre> listGenres = genreservice.listAll();
    for (Genre genre : genrerepo.findAll()) {
      log.info(genre.toString());
    }
    model.addAttribute("listGenres", listGenres);
    return "genre/list";
  }

  //Method to add new a genre
  @RequestMapping("genre/new")
  public String showNewGenreForm(Model model){
    Genre genre = new Genre();
    model.addAttribute("genre", genre);
    return "genre/new";
  }

  //method that saves changes to author

  @PostMapping(value = "/genre_save")
  public String saveGenre(@ModelAttribute("genre") Genre genre)
      throws BadResourceException, ResourceAlreadyExistsException {
    genreservice.save(genre);
    for (Genre genre1: genrerepo.findAll()) {
      log.info(genre.toString());
    }
    return "redirect:/genre/list";
  }


  // method to delete a genre
  @RequestMapping("/delete_genre/{genreid}")
  public String deleteGenre(@PathVariable(name = "genreid") Long genreid) {
    genreservice.deleteGenre(genreid);
    return "redirect:/genre/list";
  }

  //method to update
  @PostMapping(value = "genre/update")
  public String updateGenre(@ModelAttribute("genre") Genre genre)
      throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
    genreservice.update(genre);
    return "redirect:/genre/list";
  }

  // Method to create page for updating a genre
  @RequestMapping("/genre/update/{genreid}")
  public ModelAndView showUpdateGenrePage(@PathVariable(name = "genreid") Long genreid) {
    ModelAndView mav = new ModelAndView("/genre/update");
    Genre genre = genreservice.getGenre(genreid);
    final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
    log.info("---------------update author----------------");
    mav.addObject("genre", genre);
    return mav;
  }

}
