package ru.gkarmada.project.author;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;
import ru.gkarmada.project.book.Book;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private String firstName;

    @Column
    @NotEmpty
    private String lastName;

    @Column
    private String secondName;

    @Column
    private String description;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;




    public Author(String firstName, String lastName, String secondName, String description) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.description = description;
    }

    // String Methods
    @Override
    public String toString(){
        return String.format(
        "[ id=%d, firstName='%s', secondName='%s', lastName='%s', description='%s']",
        id, firstName, secondName, lastName, description);
     //         return String.format(
     //          "%s",
     //          firstName);
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
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

