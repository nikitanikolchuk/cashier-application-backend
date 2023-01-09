package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.CategoryDto;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * DTO converter class for Category and CategoryDto types.
 */
@Component
public class CategoryDtoConverter extends AbstractDtoConverter<Category, CategoryDto> {
    @Override
    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(Objects.requireNonNull(entity.getId()));
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return new Category(dto.getName());
    }
}
