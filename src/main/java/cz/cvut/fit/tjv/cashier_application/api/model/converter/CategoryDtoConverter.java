package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.CategoryDto;
import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemDto;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * DTO converter class for Category and CategoryDto types.
 */
@Component
public class CategoryDtoConverter extends AbstractDtoConverter<Category, CategoryDto> {
    MenuItemDtoConverter menuItemConverter;
    public CategoryDtoConverter(MenuItemDtoConverter menuItemConverter) {
        this.menuItemConverter = menuItemConverter;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(Objects.requireNonNull(entity.getId()));
        dto.setName(entity.getName());
        Collection<MenuItemDto> items = entity.getMenuItems().stream()
                .map(item -> menuItemConverter.toDto(item))
                .toList();
        dto.setItems(items);
        return dto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return new Category(dto.getName());
    }
}
