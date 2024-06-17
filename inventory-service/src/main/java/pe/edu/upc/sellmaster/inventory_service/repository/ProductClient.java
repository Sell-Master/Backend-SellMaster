package pe.edu.upc.sellmaster.inventory_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("api/products/{id}")
    ProductResponse getProductById(@PathVariable("id") long id); // Returns ProductResponse directly
}


