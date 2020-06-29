package ru.gkarmada.project.book;

//  Configures variables for getting data from the books table

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.genre.Genre;

@Data
// This class declares the parameters contained in the books data base
@Entity
@EqualsAndHashCode(exclude = "authors")
// lombok implementation
@Getter @Setter @NoArgsConstructor // <--- THIS is it
@Table(name="books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bookid")
  private Long bookid;
  @Column(name = "title")
  private String title;
  //@Column(name = "author")
  //private String author;
  //@Column(name = "genre")
  //private Set<Genre> genre;
  @Column(name = "isbn")
  private String isbn;
  @Column(name = "yearpub")
  private int yearpub;
  @Column(name = "publisher")
  private String publisher;
  @Column(name = "availability")
  private Boolean availability;
  @Column(name = "description")
  private String description;

  /*@ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  private Set<Author> author;*/
  /*@ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookid"),
      inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorid"))
  private Set<Author> authors;*/
  @ManyToMany
  private List<Author> authors;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  private Set<Genre> genre;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookid"),
      inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genreid"))
  private Set<Genre> genres;

  public Book(Long bookid, String title, Genre genre, String isbn, int yearpub,
      String publisher, Boolean availability, String description, Author authors){
    this.bookid = bookid;
    this.title = title;
    //this.author = author;
    //this.genre = genre;
    this.isbn = isbn;
    this.yearpub = yearpub;
    this.publisher = publisher;
    this.availability = availability;
    this.description = description;
    /*this.authors = Stream.of(authors).collect(Collectors.toSet());
    this.authors.forEach(x -> x.getBooks().add(this));*/
    this.genres = Stream.of(genre).collect(Collectors.toSet());
    this.genres.forEach(x -> x.getBooks().add(this));
  }

  // String Methods
  @Override
  public String toString(){
    return String.format(
        "[ bookid=%d, title='%s', authors='%s', genre='%s', isbn='%s', yearpub='%d', publisher='%s', availability='%b', publisher='%s']",
        bookid, title, authors, genre, isbn, yearpub, publisher, availability, description);
  }

}
