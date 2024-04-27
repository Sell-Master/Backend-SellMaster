package pe.edu.upc.sellmaster.partner_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.sellmaster.partner_service.model.entities.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Partner findByRUC(long RUC);
}

