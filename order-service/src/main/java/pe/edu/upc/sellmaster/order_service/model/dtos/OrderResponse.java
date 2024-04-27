package pe.edu.upc.sellmaster.order_service.model.dtos;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderID;
    private Date fecha;
    private double montoTotal;
    private ClientResponse client; // Assuming you want to return client details
    private UserResponse user; // Assuming you want to return user details
}
