package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.CategoryJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.Category;
import org.springframework.stereotype.Service;

/**
 * Service class for Category entity type.
 */
@Service
public class CategoryService extends AbstractCrudService<Category, Integer> {
    public CategoryService(CategoryJpaRepository repository) {
        super(repository);
    }
}
