package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InventoryRequest {
    private long productID;
    private long userID;
    private int stock;
}
