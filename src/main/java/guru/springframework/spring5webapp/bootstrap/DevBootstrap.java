package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        //Pablo
        Author pablo = new Author("Pablo", "Lucero");

        Publisher p1 = new Publisher("Pedro", "Picapiedra",
                "Av.Corrientes", "485", "2",
                null, "Buenos Aires", "Argentina", "C1043AAE");

        Book b1 = new Book("mybook", "1234", p1);
        pablo.getBooks().add(b1);
        b1.getAuthors().add(pablo);

        authorRepository.save(pablo);
        publisherRepository.save(p1);
        bookRepository.save(b1);

        //Fran
        Author fran = new Author("Fran", "Lucero");

        Publisher p2 = new Publisher("Pablo", "Marmol",
                "Av.Corrientes", "485", "2",
                null, "Buenos Aires", "Argentina", "C1043AAE");

        Book b2 = new Book("secondbook", "2233", p2);
        fran.getBooks().add(b2);
        b2.getAuthors().add(fran);

        authorRepository.save(fran);
        publisherRepository.save(p2);
        bookRepository.save(b2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
