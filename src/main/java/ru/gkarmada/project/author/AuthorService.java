package ru.gkarmada.project.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

@Service
public class AuthorService {

    // Create an author repo to create an author object
    @Autowired
    private AuthorRepository authorRepository;

    // Method that lists all authors
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    // Method that gets an Author from the database
    public Author getAuthor(Long authorid) {
        return authorRepository.findById(authorid).get();
    }

    // Method to delete a book from the database
    public void deleteAuthor(Long authorid) {
        authorRepository.deleteById(authorid);
    }

    //  Check Id to see if author exists
    private boolean existsById(Long authorid) {
        return authorRepository.existsById(authorid);
    }

    // save author
    public Author save(Author author) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(author.getFirstName())) {
            if (author.getId() != null && existsById(author.getId())) {
                throw new ResourceAlreadyExistsException("Author with id: " + author.getId() +
                        " already exists");
            }
            return authorRepository.save(author);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save author");
            exc.addErrorMessage("Author is null or empty");
            throw exc;
        }
    }

    // update book
    public void update(Author author)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(author.getId())) {
            if (!existsById(author.getId())) {
                throw new ResourceNotFoundException("Cannot find author with id: " + author.getId());
            }
            authorRepository.save(author);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save author");
            exc.addErrorMessage("Author is null or empty");
            throw exc;
        }
    }

}
