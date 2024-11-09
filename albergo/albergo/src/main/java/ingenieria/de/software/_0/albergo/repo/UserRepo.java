package ingenieria.de.software._0.albergo.repo;

import ingenieria.de.software._0.albergo.entidad.User;
import ingenieria.de.software._0.albergo.enums.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
