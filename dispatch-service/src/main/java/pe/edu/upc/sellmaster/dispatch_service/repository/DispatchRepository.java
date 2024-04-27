package pe.edu.upc.sellmaster.dispatch_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.dispatch_service.model.entities.Dispatch;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
}

