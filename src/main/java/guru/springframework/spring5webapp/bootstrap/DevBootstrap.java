package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {

        //Pablo
        Author pablo = new Author("Pablo", "Lucero");
        Book b1 = new Book("mybook", "1234", "editorial b1");
        pablo.getBooks().add(b1);
        b1.getAuthors().add(pablo);

        authorRepository.save(pablo);
        bookRepository.save(b1);

        //Fran
        Author fran = new Author("Fran", "Lucero");
        Book b2 = new Book("secondbook", "2233", "editorial aaa");
        fran.getBooks().add(b2);
        b2.getAuthors().add(fran);

        authorRepository.save(fran);
        bookRepository.save(b2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
