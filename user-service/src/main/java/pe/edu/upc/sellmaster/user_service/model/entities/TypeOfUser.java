package pe.edu.upc.sellmaster.user_service.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "typeofuser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "TypeOfUser [typeId=" + typeId + ", description=" + description + "]";
    }
}

