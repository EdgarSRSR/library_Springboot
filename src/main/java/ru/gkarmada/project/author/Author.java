package ru.gkarmada.project.author;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.book.Book;

@Data
@Entity
@Table(name="author")
// lombok implementation
@Getter
@Setter
@NoArgsConstructor
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "authorid")
  Long authorid;
  @Column(name = "name")
  String name;
  @Column(name = "lastname")
  String lastname;
  @Column(name = "secondname")
  String secondname;
  @Column(name = "description")
  String description;

  @ManyToMany(mappedBy = "authors")
  private Set<Book> books;

  public  Author(String name, String lastname, String secondname, String description){
    this.name = name;
    this.secondname = secondname;
    this.lastname = lastname;
    this.description = description;
  }

  // String Methods
  @Override
  public String toString(){
//    return String.format(
//        "[ authorid=%d, name='%s', secondname='%s', lastname='%s', description='%s']",
//        authorid, name, secondname, lastname, description);
    return String.format(
        "%s",
        name);
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(authorid, author.authorid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorid);
  }
}
