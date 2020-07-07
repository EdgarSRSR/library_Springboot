package ru.gkarmada.project.book;

import java.util.function.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification implements Specification<Book> {

  private Book filter;

  public BookSpecification(Book filter){
    super();
        this.filter = filter;
  }

  @Override
  public javax.persistence.criteria.Predicate toPredicate(Root<Book> root, CriteriaQuery<?> cq,
      CriteriaBuilder cb) {

    Predicate p = (Predicate) cb.disjunction();

    return (javax.persistence.criteria.Predicate) p;
  }

}
