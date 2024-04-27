package pe.edu.upc.sellmaster.order_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.sellmaster.order_service.model.dtos.ClientResponse;

@FeignClient(name = "client-service")
public interface ClientClient {
    @GetMapping("api/clients/{id}")
    ClientResponse getClientById(@PathVariable("id") long id);
}
