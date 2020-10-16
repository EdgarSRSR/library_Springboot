package ru.gkarmada.project.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gkarmada.project.author.Author;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long>, JpaRepository<Genre, Long>,  JpaSpecificationExecutor<Genre> {



}
