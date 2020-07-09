package ru.gkarmada.project.genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String description;

    // TODO: use JsonManagedReference, JsonBackReference to allow books list of the genre in json
    @JsonIgnore
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
                id, name, description);
    }

}
