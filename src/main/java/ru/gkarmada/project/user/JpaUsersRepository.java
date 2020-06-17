package ru.gkarmada.project.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public abstract class JpaUsersRepository implements UsersRepository {

  @PersistenceContext
  private EntityManager em;

  // This methods helps for calling the information from the database to populate the html tables

  @Override
  public List<User> findAll(){
    TypedQuery<User> query = em.createQuery("SELECT e from users", User.class);
    return ((TypedQuery) query).getResultList();
  }

  // Methods to save a user to database
  @Override
  public User save(User user){
    em.persist(user);
    return user;
  }

}
