package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
    Collection<OrderDetail> findAllByOrder_Id(Integer orderId);
}
