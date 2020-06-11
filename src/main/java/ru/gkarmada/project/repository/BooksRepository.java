package ru.gkarmada.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gkarmada.project.model.Books;

// Book repository that gets the JPA Dependencies
public interface BooksRepository extends JpaRepository<Books, Long> {

}
