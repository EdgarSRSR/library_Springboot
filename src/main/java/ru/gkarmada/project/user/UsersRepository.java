package ru.gkarmada.project.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Calls the JPA dependencies for the employees
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

  // Creates methods to get information from the users by the full name
  List<User> findByfio (String fio);

  // Creates methods to get information from the users by the email
  User findByEmail(String email);

  // Creates methods to get information from the users  by the id
  User findByUserid(Long id);

  // Creates methods to save a new user to the database
  User save(User user);
}
