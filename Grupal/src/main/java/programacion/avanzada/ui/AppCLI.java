package programacion.avanzada.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Scanner;

@ApplicationScoped
public class AppCLI {

    private final Scanner sc = new Scanner(System.in);

    @Inject
    private BookCLI bookCLI;

    @Inject
    private CustomerCLI customerCLI;

    @Inject
    private OrderCLI orderCLI;


    public void start() {

        while (true) {
            System.out.println("""
                    
                    ===== MENU =====
                    1 Books
                    2 Customers
                    3 Orders
                    4 Concurrency demo
                    0 Exit
                    """);

            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> bookCLI.menu();
                case 2 -> customerCLI.menu();
                case 3 -> orderCLI.menu();
                case 0 -> System.exit(0);
            }
        }
    }
}
