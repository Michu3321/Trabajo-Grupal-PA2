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
            throw new IllegalArgumentException("Customer required");

        if (order.getItems() == null || order.getItems().isEmpty())
            throw new IllegalArgumentException("Order without items");

        double total = calculateTotal(order.getItems());
        order.setTotal(total);

        repo.save(order);
    }

    /* lógica de negocio */
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
