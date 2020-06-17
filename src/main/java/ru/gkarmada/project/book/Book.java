package ru.gkarmada.project.book;

//  Configures variables for getting data from the books table

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This class declares the parameters contained in the books data base
@Entity
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
  @Column(name = "author")
  private String author;
  @Column(name = "isbn")
  private String isbn;
  @Column(name = "yearpub")
  private int yearpub;
  @Column(name = "publisher")
  private String publisher;
  @Column(name = "availability")
  private Boolean availability;


  public Book(Long bookid, String title, String author, String isbn, int yearpub,
      String publisher, Boolean availability){
    this.bookid = bookid;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.yearpub = yearpub;
    this.publisher = publisher;
    this.availability = availability;
  }

  // String Methods
  @Override
  public String toString(){
    return String.format(
        "[ bookid=%d, title='%s', author='%s', isbn='%s', yearpub='%d', publisher='%s', availability='%b']",
        bookid, title, author, isbn, yearpub, publisher, availability);
  }

}
