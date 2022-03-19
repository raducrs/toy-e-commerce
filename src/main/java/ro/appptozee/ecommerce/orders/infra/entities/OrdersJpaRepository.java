package ro.appptozee.ecommerce.orders.infra.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersJpaRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.userId=:uId and o.orderStatus=0")
    Optional<Order> findCart(@Param("uId") long userId);
}
