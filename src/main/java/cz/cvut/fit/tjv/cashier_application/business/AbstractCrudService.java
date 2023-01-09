package cz.cvut.fit.tjv.cashier_application.business;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

/**
 * Abstract class for a CRUD service.
 *
 * @param <E> Entity type that implements Persistable
 * @param <ID> ID type of the Entity class
 */
public abstract class AbstractCrudService<E extends Persistable<ID>, ID> {
    /**
     * JpaRepository for this entity type.
     */
    protected final JpaRepository<E, ID> repository;

    public AbstractCrudService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    /**
     * Save an entity to the repository.
     *
     * @param entity entity to save with a null id
     * @return saved entity with a generated id
     */
    public E create(E entity){
        return repository.save(entity);
    }

    /**
     * Find an entity from the repository by id.
     *
     * @param id must not be null
     * @return found Entity or Optional#empty()
     */
    public Optional<E> readById(ID id) {
        return repository.findById(id);
    }

    /**
     * Find all entities of this type.
     *
     * @return an iterable of Entity type
     */
    public Iterable<E> readAll() {
        return repository.findAll();
    }

    /**
     * Update an entity from the repository.
     *
     * @param entity entity to update with a generated id
     * @return updated entity
     * @throws EntityNotFoundException if there is no entity with this id
     */
    public E update(E entity) throws EntityNotFoundException {
        if (!repository.existsById(Objects.requireNonNull(entity.getId())))
            throw new EntityNotFoundException("Not found entity with id = " + entity.getId());
        return repository.save(entity);
    }

    /**
     * Delete an entity from the repository by id.
     *
     * @param id must not be null
     */
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
