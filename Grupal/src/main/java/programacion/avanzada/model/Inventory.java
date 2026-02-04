package programacion.avanzada.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Inventory {

    @Property("sold")
    private int sold;

    @Property("supplied")
    private int supplied;
}
