package ru.gkarmada.project.author;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gkarmada.project.book.Book;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>, JpaRepository<Author, Long>,
    JpaSpecificationExecutor<Author> {

}
