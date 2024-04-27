package pe.edu.upc.sellmaster.dispatch_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.PartnerResponse;

@FeignClient(name = "partner-service")
public interface PartnerClient {
    @GetMapping("api/partners/{id}")
    PartnerResponse getPartnerById(@PathVariable("id") long id);
}
