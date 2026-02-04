package programacion.avanzada.repository;

import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import jakarta.inject.Inject;

import java.util.List;

public abstract class BaseRepository<T> {

    @Inject
    protected Datastore datastore;

    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        datastore.save(entity);
    }

    public List<T> findAll() {
        return datastore.find(entityClass).iterator().toList();
    }

    public void deleteById(Object id) {
        datastore.find(entityClass)
                .filter(Filters.eq("_id", id))
                .delete();
    }
}
