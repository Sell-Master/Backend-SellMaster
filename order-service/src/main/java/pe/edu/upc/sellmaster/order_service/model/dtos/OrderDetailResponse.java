package pe.edu.upc.sellmaster.order_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private long orderDetailID;
    private int cantidad;
    private ProductResponse product;
    private long orderID;
}
