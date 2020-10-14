package ru.gkarmada.project.book;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public abstract class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    // this method helps for calling the information from the database to populate the html tables
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT e from books", Book.class);
        return ((TypedQuery) query).getResultList();
    }


}
