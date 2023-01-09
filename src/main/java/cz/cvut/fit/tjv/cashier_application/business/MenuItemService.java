package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.MenuItemJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.stereotype.Service;

/**
 * Service class for MenuItem entity type.
 */
@Service
public class MenuItemService extends AbstractCrudService<MenuItem, Integer> {
    MenuItemService(MenuItemJpaRepository repository) {
        super(repository);
    }
}
