package org.project.springweb.repository.order;

import java.util.List;
import java.util.Optional;
import org.project.springweb.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"items", "items.book", "user"})
    List<Order> findAllByUserId(Long id);

    @EntityGraph(attributePaths = {"items", "items.book", "user"})
    Optional<Order> findByIdAndUserId(Long id, Long userId);
}
