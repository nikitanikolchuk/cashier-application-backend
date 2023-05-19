package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.CategoryRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.CategoryResponseDto;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for Category and CategoryDto types.
 */
@Component
public class CategoryDtoConverter extends AbstractDtoConverter<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public CategoryResponseDto toDto(Category entity) {
        return new CategoryResponseDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName()
        );
    }

    @Override
    public Category toEntity(CategoryRequestDto dto) {
        return new Category(dto.name());
    }
}
