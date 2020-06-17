package ru.gkarmada.project.book;

import org.springframework.data.jpa.repository.JpaRepository;

// Book repository that gets the JPA Dependencies
public interface BooksRepository extends JpaRepository<Book, Long> {

}
