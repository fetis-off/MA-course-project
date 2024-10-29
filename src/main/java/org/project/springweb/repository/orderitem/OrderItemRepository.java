package org.project.springweb.repository.orderitem;

import java.util.Optional;
import org.project.springweb.model.OrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @EntityGraph(attributePaths = {"order.user", "order"})
    Optional<OrderItem> findByIdAndOrderIdAndOrderUserId(Long orderItemId,
                                                         Long orderId,
                                                         Long userId);
}
