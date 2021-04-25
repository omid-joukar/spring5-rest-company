package antigypt.springframework.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ApplicationPermissions permission;
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;





}
