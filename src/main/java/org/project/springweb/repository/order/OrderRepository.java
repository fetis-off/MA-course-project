package org.project.springweb.repository.order;

import java.util.Optional;
import org.project.springweb.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"orderItems", "orderItems.book", "user"})
    Page<Order> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"orderItems", "orderItems.book", "user"})
    Optional<Order> findByIdAndUserId(Long id, Long userId);
}
