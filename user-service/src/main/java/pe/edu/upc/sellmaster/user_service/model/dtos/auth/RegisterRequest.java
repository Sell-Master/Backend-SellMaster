package pe.edu.upc.sellmaster.user_service.model.dtos.auth;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long typeId;
}
