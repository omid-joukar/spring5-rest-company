package antigypt.springframework.repositories;

import antigypt.springframework.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe , Long> {
    Optional<Subscribe> findByEmail(String email);
}
