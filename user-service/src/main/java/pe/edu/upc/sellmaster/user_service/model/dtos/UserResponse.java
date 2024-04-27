package pe.edu.upc.sellmaster.user_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserResponse {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private SimpleTypeOfUserResponse typeOfUser;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SimpleTypeOfUserResponse {
        private long typeId;
        private String description;
    }
}
