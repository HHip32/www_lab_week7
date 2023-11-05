package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
