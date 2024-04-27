package pe.edu.upc.sellmaster.product_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {
    private long productID;
    private String productType;
    private String brand;
    private String additionalInfo;
    private double price;
}

