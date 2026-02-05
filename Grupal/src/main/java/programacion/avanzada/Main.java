package programacion.avanzada;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import programacion.avanzada.model.Author;
import programacion.avanzada.model.Book;
import programacion.avanzada.model.Inventory;
import programacion.avanzada.service.BookService;
import programacion.avanzada.ui.AppCLI;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Weld weld = new Weld();

        // Escanea todo el paquete base del proyecto
        weld.addPackage(true, Main.class);

        try (WeldContainer container = weld.initialize()) {

            System.out.println("¡Weld se inició correctamente!");

            // CDI crea AppCLI y hace todos los @Inject
            AppCLI appCLI = container.select(AppCLI.class).get();

            // Arranca el menú
            appCLI.start();
        }
    }
}



//public class Main {
//    public static void main(String[] args) {
//        Weld weld = new Weld();
//        // Forzamos el escaneo para asegurar que Weld encuentre BookService
//        weld.addPackage(true, Main.class);
//
//        try (WeldContainer container = weld.initialize()) {
//            System.out.println("¡Weld se inició correctamente!");
//
//            // 1. Obtener el servicio (Weld inyecta automáticamente el Repo y Datastore)
//            BookService service = container.select(BookService.class).get();
//
//            // 2. Crear el autor
////            Author autor = new Author();
////            autor.setId("auth-001");
////            autor.setName("Gabriel García Márquez");
//
//            // 3. Crear el inventario (Asegúrate de que LineItem tenga @Embedded)
////            Inventory inventario = new Inventory();
////            inventario.setSupplied(100);
////            inventario.setSold(10);
//
//            // 4. Crear el Libro completo
//            Book nuevoLibro = new Book();
//            nuevoLibro.setTitle("la culpa");
//            nuevoLibro.setIsbn("978-03074733441");
//            nuevoLibro.setPrice(28.98);
////            nuevoLibro.setAuthors(List.of(autor));
////            nuevoLibro.setInventory(inventario);
//
//            // 5. Guardar en Atlas
//            System.out.println("Intentando guardar el libro con sus autores e inventario...");
//            service.add(nuevoLibro);
//
//            System.out.println("¡ÉXITO! Libro guardado en MongoDB Atlas.");
//
//            // 6. Verificar listando los libros
//            System.out.println("Lista de libros en la base de datos:");
//            service.list().forEach(b ->
//                    System.out.println(" - " + b.getTitle() + " | Stock: " + b.getInventory().getSupplied())
//            );
//
//        } catch (Exception e) {
//            System.err.println("Error en la ejecución: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}

// El proyecto utiliza MongoDB Atlas como base NoSQL
// y accede a los datos mediante el driver oficial de MongoDB.
// El mapeo entre objetos Java y documentos MongoDB
// se realiza de forma manual en la capa repositorio,
// cumpliendo el rol de un ORM NoSQL sin utilizar un framework
// ODM como Spring Data.