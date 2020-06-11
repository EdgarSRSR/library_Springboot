package ru.gkarmada.project.books;

//  Configures variables for getting data from the books table

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// This class declares the parameters contained in the user data base
@Entity
@Table(name="books")
public class Books {

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

  public Books(){}

  public Books(Long bookid, String title, String author, String isbn, int yearpub,
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

  // Getters and Setters


  public Long getBookid() {
    return bookid;
  }

  public void setBookid(Long bookid) {
    this.bookid = bookid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getYearpub() {
    return yearpub;
  }

  public void setYearpub(int yearpub) {
    this.yearpub = yearpub;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Boolean getAvailability() {
    return availability;
  }

  public void setAvailability(Boolean availability) {
    this.availability = availability;
  }
}
