package pe.edu.upc.sellmaster.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.user_service.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

