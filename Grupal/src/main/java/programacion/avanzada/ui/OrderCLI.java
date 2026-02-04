package programacion.avanzada.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.LineItem;
import programacion.avanzada.model.PurchaseOrder;
import programacion.avanzada.service.OrderService;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ApplicationScoped
public class OrderCLI {

    private final Scanner sc = new Scanner(System.in);

    @Inject
    private OrderService service;

    public void menu() {

        System.out.print("Customer ID: ");
        String customerId = sc.nextLine();

        List<LineItem> items = new ArrayList<>();

        while (true) {
            System.out.print("Book ID (0 to finish): ");
            String bookId = sc.nextLine();
            if (bookId.equals("0")) break;

            System.out.print("Qty: ");
            int qty = Integer.parseInt(sc.nextLine());

            items.add(new LineItem(new ObjectId(bookId), qty));
        }

        service.add(new PurchaseOrder(
                null,
                new ObjectId(customerId),
                null,
                null,
                1,
                0,
                items
        ));
    }
}
