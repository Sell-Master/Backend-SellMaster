package pe.edu.upc.sellmaster.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.inventory_service.model.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}

