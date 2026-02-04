package programacion.avanzada.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity("books")
public class Book {

    @Id
    private ObjectId id;

    @Property("title")
    private String title;

    @Property("isbn")
    private String isbn;

    @Property("price")
    private double price;

    private List<Author> authors;

    private Inventory inventory;
}
