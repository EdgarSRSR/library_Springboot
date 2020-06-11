package ru.gkarmada.project.users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Calls the JPA dependencies for the employees
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

  // Creates methods to get information from the users by the full name
  List<Users> findByfio (String fio);

  // Creates methods to get information from the users by the email
  Users findByEmail(String email);

  // Creates methods to get information from the users  by the id
  Users findByUserid(Long id);

  // Creates methods to save a new user to the database
  Users save(Users users);
}
