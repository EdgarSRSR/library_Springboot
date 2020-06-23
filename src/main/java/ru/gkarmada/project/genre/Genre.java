package ru.gkarmada.project.genre;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import ru.gkarmada.project.book.Book;

@Data
@Entity
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int genreid;

  private String name;

  @ManyToMany(mappedBy = "genres")
  private Set<Book> books = new HashSet<>();

  public Genre(String name) {
    this.name = name;
  }

}
