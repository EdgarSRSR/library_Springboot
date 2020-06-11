package ru.gkarmada.project.books;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gkarmada.project.books.Books;

// Book repository that gets the JPA Dependencies
public interface BooksRepository extends JpaRepository<Books, Long> {

}
