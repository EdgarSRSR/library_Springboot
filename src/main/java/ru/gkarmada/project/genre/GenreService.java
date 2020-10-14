package ru.gkarmada.project.genre;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.author.AuthorRepository;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Service
public class GenreService {

  // Create a genre repo to create a genre object
  @Autowired
  private GenreRepository genrerepo;

  // Method that lists all genres
  public List<Genre> listAll(){
    return genrerepo.findAll();
  }

  // Method that gets an Genre from the database
  public Genre getGenre(Long genreid){
    return genrerepo.findById(genreid).get();
  }

  // Method to delete a genree from the database
  public void deleteGenre(Long genreid){
    genrerepo.deleteById(genreid);
  }

  //  Check Id to see if genre exists
  private boolean existsById(Long genreid) {
    return genrerepo.existsById(genreid);
  }

  // save genre
  public Genre save(Genre genre) throws BadResourceException, ResourceAlreadyExistsException {
    if (!StringUtils.isEmpty(genre.getName())) {
      if (genre.getGenreid() != null && existsById(genre.getGenreid())) {
        throw new ResourceAlreadyExistsException("Genre with id: " + genre.getGenreid() +
            " already exists");
      }
      return genrerepo.save(genre);
    }
    else {
      BadResourceException exc = new BadResourceException("Failed to save genre");
      exc.addErrorMessage("Genre is null or empty");
      throw exc;
    }
  }

  // update genre
  public void update(Genre genre)
      throws BadResourceException, ResourceNotFoundException {
    if (!StringUtils.isEmpty(genre.getName())) {
      if (!existsById(genre.getGenreid())) {
        throw new ResourceNotFoundException("Cannot find genre with id: " + genre.getGenreid());
      }
      genrerepo.save(genre);
    }
    else {
      BadResourceException exc = new BadResourceException("Failed to save genre");
      exc.addErrorMessage("Genre is null or empty");
      throw exc;
    }
  }

}
