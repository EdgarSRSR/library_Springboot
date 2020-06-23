package ru.gkarmada.project.author;

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
@Entity
// lombok implementation
@Getter
@Setter
@NoArgsConstructor
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long authorid;
  String name;
  String lastname;
  String secondname;
  String description;

  @ManyToMany(mappedBy = "authors")
  private Set<Book> books = new HashSet<>();

  public  Author(String name, String lastname, String secondname, String description){
    this.name = name;
    this.secondname = secondname;
    this.lastname = lastname;
    this.description = description;
  }


}
