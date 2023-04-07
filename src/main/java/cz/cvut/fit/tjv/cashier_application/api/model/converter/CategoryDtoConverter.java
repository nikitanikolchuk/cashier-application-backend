package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.CategoryDto;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for Category and CategoryDto types.
 */
@Component
public class CategoryDtoConverter extends AbstractDtoConverter<Category, CategoryDto> {
    @Override
    public CategoryDto toDto(Category entity) {
        return new CategoryDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName()
        );
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return new Category(dto.name());
    }
}
