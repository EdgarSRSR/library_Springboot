package ru.gkarmada.project.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gkarmada.project.genre.Genre;

// Calls the JPA dependencies for the employees
@Repository
public interface UsersRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long>,
    JpaSpecificationExecutor<User> {

}
