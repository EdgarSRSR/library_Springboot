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
@EqualsAndHashCode(exclude = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private int published;
    private String publisher;
    private Boolean available;
    private String description;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Author> author;*/
  /*@ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookid"),
      inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorid"))
  private Set<Author> authors;*/
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres;

    public Book(Long bookid, String title, Genre genre, String isbn, int published,
                String publisher, Boolean available, String description, Author authors) {
        this.id = bookid;
        this.title = title;
        //this.author = author;
        //this.genre = genre;
        this.isbn = isbn;
        this.published = published;
        this.publisher = publisher;
        this.available = available;
        this.description = description;
    /*this.authors = Stream.of(authors).collect(Collectors.toSet());
    this.authors.forEach(x -> x.getBooks().add(this));*/
        this.genres = Stream.of(genre).collect(Collectors.toSet());
        this.genres.forEach(x -> x.getBooks().add(this));
    }

    // String Methods
    @Override
    public String toString() {
        return String.format(
                "[ id=%d, title='%s', authors='%s', genres='%s', isbn='%s', published='%d', publisher='%s', available='%b', publisher='%s']",
                id, title, authors, genres, isbn, published, publisher, available, description);
    }

}
