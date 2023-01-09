package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.OrderDetailJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetailKey;
import org.springframework.stereotype.Service;

/**
 * Service class for OrderDetail entity type.
 */
@Service
public class OrderDetailService extends AbstractCrudService<OrderDetail, OrderDetailKey> {
    OrderDetailService(OrderDetailJpaRepository repository) {
        super(repository);
    }
}
