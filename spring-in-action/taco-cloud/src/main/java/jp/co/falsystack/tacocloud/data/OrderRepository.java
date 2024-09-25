package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Order;
import jp.co.falsystack.tacocloud.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * expect
     *
     * select *
     * from Order o
     * where o.delivery_zip = ?
     */
    List<Order> findByDeliveryZip(String deliveryZip);

    /**
     * expect
     *
     * select *
     * from Order o
     * where o.delivery_zip = ? and placed_at between ? and ?
     */
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    /**
     * expect
     *
     * select *
     * from Order o
     * where o.user_id = ?
     * order by o.placed_at
     * desc
     */
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
