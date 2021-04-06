package antigypt.springframework.repositories;

import antigypt.springframework.domain.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slide, Long> {
}
