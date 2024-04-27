package pe.edu.upc.sellmaster.order_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {
    private int cantidad;
    private long productID;
    private long orderID;
}