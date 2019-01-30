package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //alexandre
        Author erich = new Author("Erich", "Gamma");
        Publisher p1 = new Publisher("Addision-Wesley", "104 avenue Everett - MA - United States");
        publisherRepository.save(p1);
        Book dp = new Book("Design Patterns: Elements of Reusable Object-Oriented Software",
                "0-201-63361-2", p1);

        erich.getBooks().add(dp);
        dp.getAuthors().add(erich);

        authorRepository.save(erich);
        bookRepository.save(dp);


        // Donald Knuth
        Author donald = new Author("Donald", "Knuth");
        Book art = new Book("The Art of Computer Programming","0-201-03801-3", p1);
        donald.getBooks().add(art);
        art.getAuthors().add(donald);

        authorRepository.save(donald);
        bookRepository.save(art);
    }

}
