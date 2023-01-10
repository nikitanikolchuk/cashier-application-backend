package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.CategoryDto;
import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemDto;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.CategoryDtoConverter;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.MenuItemDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.CategoryService;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

/**
 * Rest controller class for Category entity type.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractCrudController<Category, CategoryDto, Integer> {
    MenuItemService menuItemService;
    MenuItemDtoConverter menuItemDtoConverter;

    public CategoryController(CategoryService categoryService, CategoryDtoConverter categoryDtoConverter,
                              MenuItemService menuItemService, MenuItemDtoConverter menuItemDtoConverter) {
        super(categoryService, categoryDtoConverter);
        this.menuItemService = menuItemService;
        this.menuItemDtoConverter = menuItemDtoConverter;
    }

    /**
     * Get all menu items of this category
     *
     * @param id id of the category
     * @return collection of menu item DTOs
     * @throws ResponseStatusException if there is no category with this id
     */
    @GetMapping("/{id}/items")
    public Collection<MenuItemDto> readItems(@PathVariable Integer id) throws ResponseStatusException {
        Category category = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return category.getMenuItems().stream().map(menuItemDtoConverter::toDto).toList();
    }

    /**
     * Change name of the category.
     *
     * @param id id of the category
     * @param name new name
     * @return DTO of the updated category
     * @throws ResponseStatusException if there is no category with this id
     */
    @PutMapping("/{id}")
    public CategoryDto updateName(@PathVariable Integer id, @RequestParam String name)  throws ResponseStatusException {
        return service.readById(id).map(category -> {
            category.setName(name);
            return dtoConverter.toDto(service.update(category));
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Add a new item to this category.
     *
     * @param id id of the category
     * @param itemId id of the item
     * @return updated collection of menu item DTOs
     * @throws ResponseStatusException if category or item id is invalid
     */
    @PutMapping("/{id}/items")
    public Collection<MenuItemDto> addItem(@PathVariable Integer id, @RequestParam Integer itemId) throws ResponseStatusException {
        Category category = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MenuItem menuItem = menuItemService.readById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (menuItem.isArchived())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        category.addMenuItem(menuItem);
        service.update(category);
        return category.getMenuItems().stream().map(menuItemDtoConverter::toDto).toList();
    }

    /**
     * Delete an item of this category.
     *
     * @param id id of the category
     * @param itemId id of the item
     * @return updated collection of menu item DTOs
     * @throws ResponseStatusException if there is no category or item with these ids
     */
    @DeleteMapping("/{id}/items")
    public Collection<MenuItemDto> deleteItem(@PathVariable Integer id, @RequestParam Integer itemId) throws ResponseStatusException {
        Category category = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MenuItem menuItem = menuItemService.readById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.removeMenuItem(menuItem);
        service.update(category);
        return category.getMenuItems().stream().map(menuItemDtoConverter::toDto).toList();
    }
}
