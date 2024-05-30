package pe.edu.upc.sellmaster.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.user_service.model.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

