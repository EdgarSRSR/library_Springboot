package ru.gkarmada.project.genre;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;
import ru.gkarmada.project.book.Book;
import ru.gkarmada.project.book.BookService;

@Service
public class GenreFormatter implements Formatter<Genre> {

    @Autowired
    GenreService genreService;

    @Override
    public String print(Genre obj, Locale locale) {
        return (obj != null ? obj.getId().toString() : "");
    }

    @Override
    public Genre parse(String text, Locale locale) throws ParseException {
        Genre genre = new Genre();
        Long id = Long.valueOf(text);
        genre.setId(id);
        //Integer id = Integer.valueOf(text);
        //return this.bookService.get(id);
        return genre;
    }

}
