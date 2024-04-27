package pe.edu.upc.sellmaster.product_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequest {
    private String productType;
    private String brand;
    private String additionalInfo;
    private double price;
}

