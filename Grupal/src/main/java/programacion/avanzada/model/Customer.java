package programacion.avanzada.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity("customers")
public class Customer {

    @Id
    private ObjectId id;

    @Property("name")
    private String name;

    @Property("email")
    private String email;
}
