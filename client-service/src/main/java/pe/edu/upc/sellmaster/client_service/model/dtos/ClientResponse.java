package pe.edu.upc.sellmaster.client_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientResponse {
    private long clientId;
    private String firstName;
    private String lastName;
    private int dni;
    private int age;
}
