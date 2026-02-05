package programacion.avanzada.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Author;
import programacion.avanzada.model.Book;
import programacion.avanzada.model.Inventory;
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
                1 Añadir libro
                2 Listar libros
                3 Actualizar el precio
                4 Eliminar libro
                5 Buscar por autor
                """);

        switch (Integer.parseInt(sc.nextLine())) {
            case 1 -> add();
            case 2 -> list();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> search();
        }
    }

    private void list() {
        service.list().forEach(book -> {
            System.out.println("---------------");
            System.out.println("ID: " + book.getId());
            System.out.println("Titulo: " + book.getTitle());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Precio: " + book.getPrice());

            if (book.getInventory() != null) {
                System.out.println("Ingresados: " + book.getInventory().getSupplied());
                System.out.println("Vendidos: " + book.getInventory().getSold());
                System.out.println("Disponibles: " +
                        (book.getInventory().getSupplied() - book.getInventory().getSold()));
            } else {
                System.out.println("Inventario: no definido");
            }
        });
    }


    private void add() {

        System.out.print("Titulo: ");
        String title = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        System.out.print("Precio: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Autor: ");
        String author = sc.nextLine();

        System.out.print("Cantidad a añadir: ");
        int supplied = Integer.parseInt(sc.nextLine());

        Inventory inventory = new Inventory();
        inventory.setSupplied(supplied);
        inventory.setSold(0);

        service.add(new Book(
                null,
                title,
                isbn,
                price,
                List.of(new Author(null, author)),
                inventory
        ));
    }

    private void update() {

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Nuevo precio: ");
        double price = Double.parseDouble(sc.nextLine());

        service.updatePrice(id, price);
    }

    private void delete() {
        System.out.print("ID: ");
        service.delete(sc.nextLine());
    }

    private void search() {
        System.out.print("Autor: ");
        service.findByAuthor(sc.nextLine())
                .forEach(System.out::println);
    }
}
