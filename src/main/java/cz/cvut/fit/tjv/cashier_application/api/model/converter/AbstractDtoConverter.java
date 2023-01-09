package cz.cvut.fit.tjv.cashier_application.api.model.converter;

/**
 * Abstract class for DTO converters.
 *
 * @param <E> entity type
 * @param <D> DTO type
 */
public abstract class AbstractDtoConverter<E, D> {
    /**
     * Convert an entity to a DTO.
     *
     * @param entity entity to convert
     * @return created dto
     */
    public abstract D toDto(E entity);

    /**
     * Convert a DTO to an entity.
     *
     * @param dto dto to convert
     * @return created entity
     */
    public abstract E toEntity(D dto);
}
