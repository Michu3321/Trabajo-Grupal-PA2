package programacion.avanzada.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity("orders")
public class PurchaseOrder {

    @Id
    private ObjectId id;

    @Property("customerId")
    private ObjectId customerId;

    @Property("placedOn")
    private Date placedOn;

    @Property("deliveredOn")
    private Date deliveredOn;

    @Property("status")
    private int status;

    @Property("total")
    private double total;

    private List<LineItem> items;
}
