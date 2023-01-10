package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.converter.AbstractDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.AbstractCrudService;
import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.StreamSupport;

/**
 * Abstract Rest CRUD controller class.
 *
 * @param <E> entity type that implements Persistable
 * @param <D> DTO type
 * @param <ID> entity ID type
 */
public abstract class AbstractCrudController<E extends Persistable<ID>, D, ID> {
    protected AbstractCrudService<E, ID> service;
    protected AbstractDtoConverter<E, D> dtoConverter;

    public AbstractCrudController(AbstractCrudService<E, ID> service, AbstractDtoConverter<E, D> converter) {
        this.service = service;
        this.dtoConverter = converter;
    }

    /**
     * Create a new entity from given DTO.
     *
     * @param dto DTO to create entity from
     * @return DTO of the created entity
     * @throws ResponseStatusException if there was an error during DTO conversion
     */
    @PostMapping
    public D create(@RequestBody D dto) throws ResponseStatusException {
        try {
            return dtoConverter.toDto(service.create(dtoConverter.toEntity(dto)));
        } catch (NullPointerException | EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    /**
     * Get DTO of an entity by its id.
     *
     * @param id id of the entity
     * @return DTO created from the entity
     * @throws ResponseStatusException if there is no entity with this id
     */
    @GetMapping("/{id}")
    public D readById(@PathVariable ID id) throws ResponseStatusException {
        E entity = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return dtoConverter.toDto(entity);
    }

    /**
     * Get DTOs of all entities of this type
     *
     * @return collection of DTOs of this entity type
     */
    @GetMapping
    public Collection<D> readAll() {
        return StreamSupport.stream(service.readAll().spliterator(), false).map(dtoConverter::toDto).toList();
    }

    /**
     * Delete entity by its id.
     *
     * @param id id of the entity
     * @throws ResponseStatusException if there is no entity with this id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) throws ResponseStatusException {
        if (service.readById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        service.deleteById(id);
    }

}
