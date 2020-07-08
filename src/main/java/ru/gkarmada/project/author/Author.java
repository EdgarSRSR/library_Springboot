package ru.gkarmada.project.author;

import java.util.HashSet;
import java.util.List;
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
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long author_id;

    @Column
    String firstname;

    @Column
    String lastname;

    @Column
    String secondname;

    @Column
    String description;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String firstname, String lastname, String secondname, String description) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.description = description;
    }

    // String Methods
    @Override
    public String toString() {
        return String.format(
                "[ authorid=%d, firstname='%s', secondname='%s', lastname='%s', description='%s']",
                author_id, firstname, secondname, lastname, description);
    }

}
