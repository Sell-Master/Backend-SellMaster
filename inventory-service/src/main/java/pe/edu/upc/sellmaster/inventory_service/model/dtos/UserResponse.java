package pe.edu.upc.sellmaster.inventory_service.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private TypeOfUserResponse typeOfUser;  // Assuming you have similar setups for TypeOfUser
}

