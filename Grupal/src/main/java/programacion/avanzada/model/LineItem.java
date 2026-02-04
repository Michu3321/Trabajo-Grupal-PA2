package programacion.avanzada.model;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Property;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embedded
public class LineItem {

    @Property("bookId")
    private ObjectId bookId;

    @Property("quantity")
    private int quantity;
}
