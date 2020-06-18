package ru.gkarmada.project.book;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.gkarmada.project.exception.BadResourceException;
import ru.gkarmada.project.exception.ResourceAlreadyExistsException;
import ru.gkarmada.project.exception.ResourceNotFoundException;

// Gets the book services for the table and data base manipulation
@Service
public class BooksService {

  // Create a book repo to create a book object
  @Autowired
  private BooksRepository bookrepo;

  // Method that lists all book from the table
  public List<Book> listAll(){
    return bookrepo.findAll();
  }

  // Method that saves a new book to the data base
  //uri change
  //public void save(Book book){
  //  bookrepo.save(book);
  //}

  // Method that gets a book from the database
  public Book get(Long bookid){
    return bookrepo.findById(bookid).get();
  }

  // Method to delete a book from the database
  public void delete(Long id){
    bookrepo.deleteById(id);
  }

  /////// URI RESTRUCTURE //////////

  private boolean existsById(Long id) {
    return bookrepo.existsById(id);
  }

  // find by id
  public Book findById(Long bookid) throws ResourceNotFoundException {
    Book book = bookrepo.findById(bookid).orElse(null);
    if(book == null){
      throw new ResourceNotFoundException("no book with id " + bookid);
    }
    else return book;
  }

  public List<Book> findAll(int pageNumber, int rowPerPage) {
    List<Book> books = new ArrayList<>();
    bookrepo.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(books::add);
    return books;
  }

  public List<Book> findAllByTitle(String title, int pageNumber, int rowPerPage) {
    Book filter = new Book();
    filter.setTitle(title);
    Specification<Book> spec = new BookSpecification(filter);

    List<Book> books = new ArrayList<>();
    bookrepo.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(books::add);
    return books;
  }

  public Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException {
    if (!StringUtils.isEmpty(book.getTitle())) {
      if (book.getBookid() != null && existsById(book.getBookid())) {
        throw new ResourceAlreadyExistsException("Book with id: " + book.getBookid() +
            " already exists");
      }
      return bookrepo.save(book);
    }
    else {
      BadResourceException exc = new BadResourceException("Failed to save book");
      exc.addErrorMessage("Book is null or empty");
      throw exc;
    }
  }

  public void update(Book book)
      throws BadResourceException, ResourceNotFoundException {
    if (!StringUtils.isEmpty(book.getTitle())) {
      if (!existsById(book.getBookid())) {
        throw new ResourceNotFoundException("Cannot find book with id: " + book.getBookid());
      }
      bookrepo.save(book);
    }
    else {
      BadResourceException exc = new BadResourceException("Failed to save book");
      exc.addErrorMessage("Book is null or empty");
      throw exc;
    }
  }


  public void deleteById(Long id) throws ResourceNotFoundException {
    if (!existsById(id)) {
      throw new ResourceNotFoundException("Cannot find book with id: " + id);
    }
    else {
      bookrepo.deleteById(id);
    }
  }

  public Long count() {
    return bookrepo.count();
  }


}
