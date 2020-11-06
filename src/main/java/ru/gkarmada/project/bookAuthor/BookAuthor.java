package ru.gkarmada.project.bookAuthor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.book.Book;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookAuthor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "book_id")
  Book book;

  @ManyToOne
  @JoinColumn(name = "author_id")
  Author author;

}
