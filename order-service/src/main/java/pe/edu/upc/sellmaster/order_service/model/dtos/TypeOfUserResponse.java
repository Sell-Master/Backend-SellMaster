package pe.edu.upc.sellmaster.order_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserResponse {
    private long typeId;
    private String description;
}
