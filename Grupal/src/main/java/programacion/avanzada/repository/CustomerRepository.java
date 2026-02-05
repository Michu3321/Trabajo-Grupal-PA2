package programacion.avanzada.repository;

import jakarta.enterprise.context.ApplicationScoped;
import programacion.avanzada.model.Customer;

@ApplicationScoped
public class CustomerRepository extends BaseRepository<Customer> {

    public CustomerRepository() {
        super(Customer.class);
    }
}
