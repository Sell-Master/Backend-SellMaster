package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDetailResponse {
    private long inventoryID;
    private ProductResponse product;
    private UserResponse user;
    private int stock;
}

