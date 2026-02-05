package programacion.avanzada.repository;

import jakarta.enterprise.context.ApplicationScoped;
import programacion.avanzada.model.PurchaseOrder;

@ApplicationScoped
public class OrderRepository extends BaseRepository<PurchaseOrder> {

    public OrderRepository() {
        super(PurchaseOrder.class);
    }
}
