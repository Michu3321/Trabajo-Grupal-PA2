package programacion.avanzada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Book;
import programacion.avanzada.model.Inventory;
import programacion.avanzada.repository.BookRepository;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository repo;

    public void add(Book b) {
        if (b.getTitle() == null || b.getTitle().isBlank())
            throw new IllegalArgumentException("Titulo del libro requerido");

        if (b.getPrice() < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        if (b.getInventory() == null) {
            b.setInventory(new Inventory(0, 0));
        }

        repo.save(b);
    }

    public List<Book> list() {
        return repo.findAll();
    }

    public List<Book> findByAuthor(String author) {
        if (author == null || author.isBlank())
            throw new IllegalArgumentException("Author requerido");

        return repo.findByAuthor(author);
    }

    public void updatePrice(String id, double price) {
        if (price < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        Book book = repo.findAll().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.setPrice(price);
        repo.save(book);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}