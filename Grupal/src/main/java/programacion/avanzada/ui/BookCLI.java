package programacion.avanzada.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Author;
import programacion.avanzada.model.Book;
import programacion.avanzada.service.BookService;

import java.util.List;
import java.util.Scanner;

@ApplicationScoped
public class BookCLI {

    private final Scanner sc = new Scanner(System.in);

    @Inject
    private BookService service;

    public void menu() {

        System.out.println("""
                1 Add
                2 List
                3 Update price
                4 Delete
                5 Search by author
                """);

        switch (Integer.parseInt(sc.nextLine())) {
            case 1 -> add();
            case 2 -> service.list().forEach(System.out::println);
            case 3 -> update();
            case 4 -> delete();
            case 5 -> search();
        }
    }

    private void add() {

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Author: ");
        String author = sc.nextLine();

        service.add(new Book(
                null,
                title,
                isbn,
                price,
                List.of(new Author(null, author)),
                null
        ));
    }

    private void update() {

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("New price: ");
        double price = Double.parseDouble(sc.nextLine());

        service.updatePrice(id, price);
    }

    private void delete() {
        System.out.print("ID: ");
        service.delete(sc.nextLine());
    }

    private void search() {
        System.out.print("Author: ");
        service.findByAuthor(sc.nextLine())
                .forEach(System.out::println);
    }
}
