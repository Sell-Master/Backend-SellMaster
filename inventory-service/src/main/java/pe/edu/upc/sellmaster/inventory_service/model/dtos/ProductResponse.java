package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private long productID;
    private String productType;
    private String brand;
    private String additionalInfo;
    private double price;
}
