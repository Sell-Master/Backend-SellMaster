package pe.edu.upc.sellmaster.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.order_service.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

