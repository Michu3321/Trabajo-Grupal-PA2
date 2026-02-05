package programacion.avanzada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.*;
import programacion.avanzada.repository.BookRepository;
import programacion.avanzada.repository.OrderRepository;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository repo;

    @Inject
    private BookRepository bookRepo;

    public void add(PurchaseOrder order) {

        if (order.getCustomerId() == null)
            throw new IllegalArgumentException("Cliente requerido");

        if (order.getItems() == null || order.getItems().isEmpty())
            throw new IllegalArgumentException("Pedido sin artículos");

        for (LineItem item : order.getItems()) {

            Book book = bookRepo.findAll().stream()
                    .filter(b -> b.getId().equals(item.getBookId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

            Inventory inv = book.getInventory();

            if (inv == null)
                throw new IllegalStateException("Libro sin inventario");

            int available = inv.getSupplied() - inv.getSold();

            if (item.getQuantity() > available)
                throw new IllegalArgumentException("Stock insuficiente para el libro: " + book.getTitle());

            inv.setSold(inv.getSold() + item.getQuantity());

            bookRepo.save(book);
        }

        double total = calculateTotal(order.getItems());
        order.setTotal(total);

        repo.save(order);
    }

    private double calculateTotal(List<LineItem> items) {

        double total = 0;

        for (LineItem item : items) {

            Book b = bookRepo.findAll().stream()
                    .filter(book -> book.getId().equals(item.getBookId()))
                    .findFirst()
                    .orElseThrow();

            total += b.getPrice() * item.getQuantity();
        }

        return total;
    }
}
