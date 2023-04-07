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
        return new MenuItemDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName(),
                entity.getPrice()
        );
    }

    @Override
    public MenuItem toEntity(MenuItemDto dto) {
        return new MenuItem(dto.name(), dto.price());
    }
}
