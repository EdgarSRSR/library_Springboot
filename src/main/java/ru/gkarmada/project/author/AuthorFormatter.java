package ru.gkarmada.project.author;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

@Service
public class AuthorFormatter implements Formatter<Author> {

  @Autowired
  AuthorService authorService;

  @Override
  public String print(Author obj, Locale locale){
    return(obj != null ? obj.getId().toString() : "");
  }

  @Override
  public Author parse(String text, Locale locale) throws ParseException {
    Author author = new Author();
    Long id = Long.valueOf(text);
    author.setId(id);
    //Integer id = Integer.valueOf(text);
   // return this.authorService.get(id);
    return author;
  }

}
