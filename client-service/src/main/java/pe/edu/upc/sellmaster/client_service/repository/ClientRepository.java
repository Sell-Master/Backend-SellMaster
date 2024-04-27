package pe.edu.upc.sellmaster.client_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.client_service.model.entities.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByDni(int dni);
    Long countByDni(int dni);
    List<Client> findByAge(int age);
}

