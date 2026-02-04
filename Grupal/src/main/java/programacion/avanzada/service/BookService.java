package programacion.avanzada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Book;
import programacion.avanzada.repository.BookRepository;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository repo;

    /* CREATE */
    public void add(Book b) {

        if (b.getTitle() == null || b.getTitle().isBlank())
            throw new IllegalArgumentException("Title required");

        if (b.getPrice() < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        repo.save(b);
    }

    /* READ */
    public List<Book> list() {
        return repo.findAll();
    }

    /* SEARCH */
    public List<Book> findByAuthor(String author) {

        if (author == null || author.isBlank())
            throw new IllegalArgumentException("Author required");

        return repo.findByAuthor(author);
    }

    /* UPDATE */
    public void updatePrice(String id, double price) {

        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        Book book = repo.findAll().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow();

        book.setPrice(price);
        repo.save(book);
    }

    /* DELETE */
    public void delete(String id) {
        repo.deleteById(id);
    }
}
