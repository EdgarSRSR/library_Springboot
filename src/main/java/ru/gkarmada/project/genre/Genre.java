package ru.gkarmada.project.genre;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gkarmada.project.book.Book;

@Data
@Entity
@Table(name = "genre")
// lombok implementation
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    String name;

    String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<Book> books;


    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // String Methods
    @Override
    public String toString() {
        return String.format(
                "[ genreid=%d, name='%s', description='%s']",
                id, name, description);
    }

}
