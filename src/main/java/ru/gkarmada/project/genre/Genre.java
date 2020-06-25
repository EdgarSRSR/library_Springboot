package ru.gkarmada.project.genre;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.book.Book;

@Data
// lombok implementation
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long genreid;

  private String name;

  @ManyToMany(mappedBy = "genres")
  private Set<Book> books = new HashSet<>();

  public Genre(String name) {
    this.name = name;
  }

}
