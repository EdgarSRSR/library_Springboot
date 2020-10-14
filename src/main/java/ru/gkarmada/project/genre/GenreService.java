package ru.gkarmada.project.genre;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.gkarmada.project.ProjectApplication;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Service
public class GenreService {

    // Create a genre repo to create a genre object
    @Autowired
    private GenreRepository genreRepository;

    // Method that lists all genres
    public List<Genre> listAll() {
        return genreRepository.findAll();
    }

    // Method that gets an Genre from the database
    public Genre findById(Long genreId) throws ResourceNotFoundException {
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if(genreOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find genre with id: " + genreId);
        }
        return genreRepository.getOne(genreId);
    }

    // Method to delete a genre from the database
    public void delete(Genre genre) {
        genreRepository.deleteById(genre.getId());
    }

    // Method to delete a book from the database
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    //  Check Id to see if genre exists
    private boolean existsById(Long genreId) {
        return genreRepository.existsById(genreId);
    }

    // save genre
    public Genre save(Genre genre) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(genre.getName())) {
            if (genre.getId() != null && existsById(genre.getId())) {
                throw new ResourceAlreadyExistsException("Genre Id: " + genre.getId() +
                        " already exists");
            }
            return genreRepository.save(genre);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save genre");
            exc.addErrorMessage("Genre is null or empty");
            throw exc;
        }
    }

    // update genre
    public void update(Genre genre)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(genre.getName())) {
            if (!existsById(genre.getId())) {
                throw new ResourceNotFoundException("Cannot find genre with id: " + genre.getId());
            }
            genreRepository.save(genre);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save genre");
            exc.addErrorMessage("Genre is null or empty");
            throw exc;
        }
    }


}
