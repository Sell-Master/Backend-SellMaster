package pe.edu.upc.sellmaster.dispatch_service.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DispatchRequest {
    private long partnerID;
    private long productID;
    private long userID;
    private int quantity;
    private Date date; // Ensure this matches the Date type used in the entity
}


