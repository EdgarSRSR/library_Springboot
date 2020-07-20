package ru.gkarmada.project.author;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Author findByLastName(Set<Author> lastname);
}
