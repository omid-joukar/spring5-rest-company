package antigypt.springframework.repositories;

import antigypt.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByLastName(String lastName);
}
