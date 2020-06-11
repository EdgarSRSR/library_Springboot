package ru.gkarmada.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.gkarmada.project.model.Books;
import ru.gkarmada.project.repository.BooksRepository;

@Repository
public abstract class JpaBooksRepository implements BooksRepository {

  @PersistenceContext
  private EntityManager em;

  // this method helps for calling the information from the database to populate the html tables
  @Override
  public List<Books> findAll(){
    TypedQuery<Books> query = em.createQuery("SELECT e from books", Books.class);
    return ((TypedQuery) query).getResultList();
  }

  // Methods to save a book to Database
  @Override
  public Books save(Books book){
    em.persist(book);
    return book;
  }

}
