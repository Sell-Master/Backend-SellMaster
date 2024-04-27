package pe.edu.upc.sellmaster.dispatch_service.model.dtos;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DispatchDetailResponse {
    private long dispatchID;
    private PartnerResponse partner;
    private ProductResponse product;
    private UserResponse user; // Add this field
    private int quantity;
    private Date date;
}

