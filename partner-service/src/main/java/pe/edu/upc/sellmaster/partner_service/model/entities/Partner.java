package pe.edu.upc.sellmaster.partner_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partnerID;

    @Column(name = "partner_name", nullable = false, length = 100)
    private String partnerName;

    @Column(name = "RUC", nullable = false, unique = true, length = 11)
    private long RUC;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Override
    public String toString() {
        return "Partner [partnerID=" + partnerID + ", partnerName=" + partnerName + ", RUC=" + RUC + ", additionalInfo=" + additionalInfo + "]";
    }
}
