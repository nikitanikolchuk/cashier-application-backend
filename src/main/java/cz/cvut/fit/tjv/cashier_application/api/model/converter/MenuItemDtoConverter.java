package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemResponseDto;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for MenuItem and MenuItemDto types.
 */
@Component
public class MenuItemDtoConverter extends AbstractDtoConverter<MenuItem, MenuItemRequestDto, MenuItemResponseDto> {
    @Override
    public MenuItemResponseDto toDto(MenuItem entity) {
        return new MenuItemResponseDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName(),
                entity.getPrice()
        );
    }

    @Override
    public MenuItem toEntity(MenuItemRequestDto dto) {
        return new MenuItem(dto.name(), dto.price());
    }
}
