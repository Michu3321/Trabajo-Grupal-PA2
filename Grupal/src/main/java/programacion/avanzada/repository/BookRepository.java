package programacion.avanzada.repository;

import dev.morphia.query.filters.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import programacion.avanzada.model.Book;

import java.util.List;

@ApplicationScoped
public class BookRepository extends BaseRepository<Book> {

    public BookRepository() {
        super(Book.class);
    }

    public List<Book> findByAuthor(String authorName) {
        return datastore.find(Book.class)
                .filter(Filters.eq("authors.name", authorName))
                .iterator()
                .toList();
    }
}