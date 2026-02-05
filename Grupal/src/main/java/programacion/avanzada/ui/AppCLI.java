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
                    1 Libros
                    2 Clientes
                    3 Compra
                    0 Salir
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
