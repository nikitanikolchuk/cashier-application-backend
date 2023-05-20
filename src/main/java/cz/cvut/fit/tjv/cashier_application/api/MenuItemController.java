package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemResponseDto;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.MenuItemDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.StreamSupport;

/**
 * Rest controller class for MenuItem entity type.
 */
@RestController
@RequestMapping("/items")
public class MenuItemController extends AbstractCrudController<MenuItem, MenuItemRequestDto, MenuItemResponseDto, Integer> {
    public MenuItemController(MenuItemService service) {
        super(service, new MenuItemDtoConverter());
    }

    /**
     * Get DTOs of all menu items that are not archived.
     *
     * @return collection of menu item DTOs
     */
    @Override
    @GetMapping
    public Collection<MenuItemResponseDto> readAll() {
        return StreamSupport.stream(((MenuItemService) service).readAllActive().spliterator(), false)
                .map(dtoConverter::toDto)
                .toList();
    }

    /**
     * Get DTOs of all archived menu items.
     *
     * @return collection of menu item DTOs
     */
    @GetMapping("/archive")
    public Collection<MenuItemResponseDto> readArchived() {
        return StreamSupport.stream(((MenuItemService) service).readAllArchived().spliterator(), false)
                .map(dtoConverter::toDto)
                .toList();
    }

    /**
     * Archive a menu item by id.
     *
     * @param id id of the menu item
     * @throws ResponseStatusException if there is no menu item with this id
     */
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws ResponseStatusException {
        MenuItem menuItem = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        menuItem.deleteFromCategories();
        menuItem.setArchived(true);
        service.update(menuItem);
    }
}
