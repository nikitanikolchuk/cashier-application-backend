package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemDto;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for MenuItem and MenuItemDto types.
 */
@Component
public class MenuItemDtoConverter extends AbstractDtoConverter<MenuItem, MenuItemDto> {
    @Override
    public MenuItemDto toDto(MenuItem entity) {
        MenuItemDto dto = new MenuItemDto();
        dto.setId(Objects.requireNonNull(entity.getId()));
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public MenuItem toEntity(MenuItemDto dto) {
        return new MenuItem(dto.getName(), dto.getPrice());
    }
}
