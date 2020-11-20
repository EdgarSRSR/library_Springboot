package ru.gkarmada.project.config;

import java.text.ParseException;
import java.util.Locale;
import javax.annotation.Resource;

import ru.gkarmada.project.author.Author;
import ru.gkarmada.project.author.AuthorRepository;

public class PartTwoWayConverter {

    /**
     * The string that represents null.
     */
    private static final String NULL_REPRESENTATION = "null";

    @Resource
    private AuthorRepository authorRepository;

    public PartTwoWayConverter() {
        super();
    }

    public Author parse(final String text, final Locale locale) throws ParseException {
        if (text.equals(NULL_REPRESENTATION)) {
            return null;
        }
        try {
            Long id = Long.parseLong(text);
            // Part part = partRepository.findByID(id); // this does not work with controller
            Author author = new Author(); // this works
            author.setId(id);         //
            return author;
        } catch (NumberFormatException e) {
            throw new RuntimeException("could not convert `" + text + "` to an valid id");
        }
    }

    public String print(final Author author, final Locale locale) {
        if (author.equals(NULL_REPRESENTATION)) {
            return null;
        }
        try {
            return author.getId().toString();
        } catch (NumberFormatException e) {
            throw new RuntimeException("could not convert `" + author + "` to an valid id");
        }
    }


}
