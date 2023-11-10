package massimomauro.S6L5DevelopmentSpringWebService.repositories;

import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
