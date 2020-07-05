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
  Long genreid;
  String name;
  String description;
  @ManyToMany(mappedBy = "genres")
  private Set<Book> books = new HashSet<>();


  public Genre(String name, String description) {
    this.name = name;
    this.description = description;
  }

  // String Methods
  @Override
  public String toString(){
    return String.format(
        "[ genreid=%d, name='%s', description='%s']",
        genreid, name, description);
  }

}
