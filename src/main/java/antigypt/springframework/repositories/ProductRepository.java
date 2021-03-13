package antigypt.springframework.repositories;


import antigypt.springframework.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByProductName(String productName);
}
