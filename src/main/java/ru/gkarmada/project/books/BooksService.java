package ru.gkarmada.project.books;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Gets the book services for the table and data base manipulation
@Service
public class BooksService {

  // Create a book repo to create a book object
  @Autowired
  private BooksRepository bookrepo;

  // Method that lists all book from the table
  public List<Books> listAll(){
    return bookrepo.findAll();
  }

  // Method that saves a new book to the data base
  public void save(Books book){
    bookrepo.save(book);
  }

  // Method that gets a book from the database
  public Books get(Long bookid){
    return bookrepo.findById(bookid).get();
  }

  // Method to delete a book from the database
  public void delete(Long id){
    bookrepo.deleteById(id);
  }

}
