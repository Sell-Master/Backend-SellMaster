package pe.edu.upc.sellmaster.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.product_service.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

