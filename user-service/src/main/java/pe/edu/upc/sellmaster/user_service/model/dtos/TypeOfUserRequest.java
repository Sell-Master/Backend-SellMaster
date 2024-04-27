package pe.edu.upc.sellmaster.user_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserRequest {
    private String description;
}
