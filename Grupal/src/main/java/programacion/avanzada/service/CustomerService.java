package programacion.avanzada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import programacion.avanzada.model.Customer;
import programacion.avanzada.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository repo;

    public void add(Customer c) {

        if (c.getName() == null || c.getName().isBlank())
            throw new IllegalArgumentException("Name required");

        if (c.getEmail() == null || !c.getEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email");

        repo.save(c);
    }

    public List<Customer> list() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
