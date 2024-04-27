package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InventoryResponse {
    private long inventoryID;
    private long productID;
    private long userID;
    private int stock;
}
