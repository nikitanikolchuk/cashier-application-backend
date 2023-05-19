package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.MenuItemResponseDto;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.CategoryDtoConverter;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.MenuItemDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.CategoryService;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CategoryController.class)
public class CategoryApiTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MenuItemDtoConverter menuItemDtoConverter;
    @MockBean
    CategoryService categoryService;
    @MockBean
    CategoryDtoConverter categoryDtoConverter;
    @MockBean
    MenuItemService menuItemService;

    @Test
    void readItems() throws Exception {
        MenuItem item1 = mock(MenuItem.class);
        when(item1.getId()).thenReturn(1);
        when(item1.getName()).thenReturn("test1");
        when(item1.getPrice()).thenReturn(100);

        MenuItemResponseDto itemDto1 = new MenuItemResponseDto(
                Objects.requireNonNull(item1.getId()),
                item1.getName(),
                item1.getPrice()
        );

        when(menuItemDtoConverter.toDto(item1)).thenReturn(itemDto1);

        MenuItem item2 = mock(MenuItem.class);
        when(item2.getId()).thenReturn(2);
        when(item2.getName()).thenReturn("test2");
        when(item2.getPrice()).thenReturn(200);

        MenuItemResponseDto itemDto2 = new MenuItemResponseDto(
                Objects.requireNonNull(item2.getId()),
                item2.getName(),
                item2.getPrice()
        );

        when(menuItemDtoConverter.toDto(item2)).thenReturn(itemDto2);

        Set<MenuItem> items = new HashSet<>(new ArrayList<>() {{
            add(item1);
            add(item2);
        }});

        Category category = mock(Category.class);
        when(category.getMenuItems()).thenReturn(items);

        when(categoryService.readById(1)).thenReturn(Optional.of(category));

        String result = mockMvc.perform(get("/categories/{id}/items", 1))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        String item1String = "{\"id\":1,\"name\":\"test1\",\"price\":100}";
        String item2String = "{\"id\":2,\"name\":\"test2\",\"price\":200}";

        Assertions.assertTrue(("[" + item1String + "," + item2String + "]").equals(result) ||
                ("[" + item2String + "," + item1String + "]").equals(result));
    }
}
