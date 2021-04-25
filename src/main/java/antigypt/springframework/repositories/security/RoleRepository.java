package antigypt.springframework.repositories.security;

import antigypt.springframework.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
