package pe.edu.upc.sellmaster.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.order_service.model.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}

