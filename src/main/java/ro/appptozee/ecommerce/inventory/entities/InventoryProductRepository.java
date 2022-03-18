package ro.appptozee.ecommerce.inventory.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductRepository extends JpaRepository<InventoryProduct,Long> {
    @Modifying
    @Query("update InventoryProduct set quantity=quantity-:q where productId=:pId")
    void reserve(@Param("pId") long productId,@Param("q") int quantity);

    @Modifying
    @Query("update InventoryProduct set quantity=quantity+:q where productId=:pId")
    void checkin(@Param("pId") long productId,@Param("q") int quantity);

}
