package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MenuItemJpaRepository extends JpaRepository<MenuItem, Integer> {
    Collection<MenuItem> findAllByArchivedIsFalse();

    Collection<MenuItem> findAllByArchivedIsTrue();
}
