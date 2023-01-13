package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.OrderDetailJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class OrderServiceTest {
    @MockBean
    OrderDetailJpaRepository orderDetailJpaRepository;
    @Autowired
    OrderService orderService;

    @Test
    void calculatePrice() {
        MenuItem item1 = mock(MenuItem.class);
        Mockito.when(item1.getId()).thenReturn(1);
        Mockito.when(item1.getPrice()).thenReturn(100);
        MenuItem item2 = mock(MenuItem.class);
        Mockito.when(item2.getId()).thenReturn(2);
        Mockito.when(item2.getPrice()).thenReturn(40);

        Order order = mock(Order.class);
        Mockito.when(order.getId()).thenReturn(1);

        ArrayList<OrderDetail> details = new ArrayList<>() {{
            add(new OrderDetail(order, item1, 1));
            add(new OrderDetail(order, item2, 2));
        }};

        Mockito.when(orderDetailJpaRepository.findAllByOrder_Id(order.getId())).thenReturn(details);
        Assertions.assertEquals(180, orderService.calculatePrice(order.getId()));
    }
}
