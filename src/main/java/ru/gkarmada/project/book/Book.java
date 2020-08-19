package ru.gkarmada.project.book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.genre.Genre;

@Data
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long book_id;

    @Column
    String title;

    @Column
    String isbn;

    @Column
    int published;

    @Column
    String publisher;

    @Column
    Boolean availability;

    @Column
    String description;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Author> author;*/
  /*@ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookid"),
      inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorid"))
  private Set<Author> authors;*/
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Genre> genres;


    public Book(Long book_id, String title, Genre genre, String isbn, int published,
                String publisher, Boolean availability, String description, Author author) {
        this.book_id = book_id;
        this.title = title;
        this.isbn = isbn;
        this.published = published;
        this.publisher = publisher;
        this.availability = availability;
        this.description = description;
        this.authors = Stream.of(author).collect(Collectors.toSet());
        this.authors.forEach(x -> x.getBooks().add(this));
        this.genres = Stream.of(genre).collect(Collectors.toSet());
        this.genres.forEach(x -> x.getBooks().add(this));
    }

    // String Methods
    @Override
    public String toString() {
        return String.format(
                "[ book_id=%d, title='%s', authors='%s', genres='%s', isbn='%s', published='%d', publisher='%s', availability='%b', description='%s']",
                book_id, title, authors, genres, isbn, published, publisher, availability, description);
    }

}
