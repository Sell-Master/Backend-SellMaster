package pe.edu.upc.sellmaster.order_service.model.dtos;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Date fecha;
    private double montoTotal;
    private long clientID;
    private long userID;
}
