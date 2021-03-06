package ru.gkarmada.project.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

// Gets the book services for the table and data base manipulation
@Service
public class BookService {

    // Create a book repo to create a book object
    @Autowired
    private BookRepository bookRepository;

    // Method that lists all book from the table
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    // Method that gets a book from the database
    public Book get(Long bookid) {
        return bookRepository.findById(bookid).get();
    }

    // Method to delete a book from the database
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    // find by id
    public Book findById(Long bookid) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookid).orElse(null);
        if (book == null) {
            throw new ResourceNotFoundException("no book with id " + bookid);
        } else return book;
    }

    // create a list of all books
    public List<Book> findAll(int pageNumber, int rowPerPage) {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(books::add);
        return books;
    }

    // find books by title
    public List<Book> findAllByTitle(String title, int pageNumber, int rowPerPage) {
        Book filter = new Book();
        filter.setTitle(title);
        Specification<Book> spec = new BookSpecification(filter);
        List<Book> books = new ArrayList<>();
        bookRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(books::add);
        return books;
    }

    // save book
    public Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(book.getTitle())) {
            if (book.getId() != null && existsById(book.getId())) {
                throw new ResourceAlreadyExistsException("Book with id: " + book.getId() +
                        " already exists");
            }
            return bookRepository.save(book);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save book");
            exc.addErrorMessage("Book is null or empty");
            throw exc;
        }
    }

    // update book
    public void update(Book book)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(book.getTitle())) {
            if (!existsById(book.getId())) {
                throw new ResourceNotFoundException("Cannot find book with id: " + book.getId());
            }
            bookRepository.save(book);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save book");
            exc.addErrorMessage("Book is null or empty");
            throw exc;
        }
    }

    // delete a book by Id
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find book with id: " + id);
        } else {
            bookRepository.deleteById(id);
        }
    }

    // count book
    public Long count() {
        return bookRepository.count();
    }

    public Page find(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

}
