package antigypt.springframework.repositories;

import antigypt.springframework.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order ,Long> {
}
