package ru.gkarmada.project.genre;

import ru.gkarmada.project.book.Book;

import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long genre_id;

    String name;
    String description;

    @ManyToMany(mappedBy = "genres")
    Set<Book> books;


    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // String Methods
    @Override
    public String toString() {
        return String.format(
                "[ id=%d, name='%s', description='%s']",
                genre_id, name, description);
    }

}
