package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemDto;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MenuItemControllerTest {
    @MockBean
    MenuItemService menuItemService;

    @Autowired
    MenuItemController menuItemController;

    @Test
    void readById() {
        MenuItem item = mock(MenuItem.class);
        when(item.getId()).thenReturn(1);
        when(item.getName()).thenReturn("test");
        when(item.getPrice()).thenReturn(100);

        when(menuItemService.readById(1)).thenReturn(Optional.of(item));

        MenuItemDto result = menuItemController.readById(1);
        Assertions.assertEquals(1, result.id());
        Assertions.assertEquals("test", result.name());
        Assertions.assertEquals(100, result.price());
    }
}
