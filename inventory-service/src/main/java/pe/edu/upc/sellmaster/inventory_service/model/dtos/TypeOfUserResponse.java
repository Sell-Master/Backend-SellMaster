package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfUserResponse {
    private long typeId;
    private String description;
}

