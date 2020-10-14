package ru.gkarmada.project.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

// Book repository that gets the JPA Dependencies
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaRepository<Book, Long>,
    JpaSpecificationExecutor<Book> {

}
