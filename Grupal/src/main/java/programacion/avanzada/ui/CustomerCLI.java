package programacion.avanzada.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Customer;
import programacion.avanzada.service.CustomerService;

import java.util.Scanner;

@ApplicationScoped
public class CustomerCLI {

    private final Scanner sc = new Scanner(System.in);

    @Inject
    private CustomerService service;

    public void menu() {

        System.out.println("""
                1 Add
                2 List
                """);

        switch (Integer.parseInt(sc.nextLine())) {
            case 1 -> add();
            case 2 -> service.list().forEach(System.out::println);
        }
    }

    private void add() {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        service.add(new Customer(null, name, email));
    }
}
