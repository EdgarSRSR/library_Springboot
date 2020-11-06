package ru.gkarmada.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import ru.gkarmada.project.author.AuthorFormatter;
import ru.gkarmada.project.genre.GenreFormatter;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebMvcConfig implements WebMvcConfigurer {

    private ApplicationContext applicationContext;

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }

    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jquery/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.5.1/");

        //
        // http://somedomain/SomeContextPath/popper/popper.min.js
        //
        registry.addResourceHandler("/popper/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/2.0.2/umd/");

        // http://somedomain/SomeContextPath/bootstrap/css/bootstrap.min.css
        // http://somedomain/SomeContextPath/bootstrap/js/bootstrap.min.js
        //
        registry.addResourceHandler("/bootstrap/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.5.0/");

    }

    @Autowired //Without autowire, this solution may not work
    private AuthorFormatter authorFormatter;

    @Autowired //Without autowire, this solution may not work
    private GenreFormatter genreFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(authorFormatter);
        registry.addFormatter(genreFormatter);
    }

}
