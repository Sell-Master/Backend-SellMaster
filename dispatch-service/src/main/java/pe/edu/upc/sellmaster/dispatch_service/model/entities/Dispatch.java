package pe.edu.upc.sellmaster.dispatch_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "dispatches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dispatchID;

    @Column(nullable = false)
    private long partnerID;

    @Column(nullable = false)
    private long productID;

    @Column(nullable = false)
    private long userID; // Add this field to store User ID

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // To store both date and time
    private Date date;

    @Override
    public String toString() {
        return "Dispatch [dispatchID=" + dispatchID + ", partnerID=" + partnerID + ", productID=" + productID + ", userID=" + userID + ", quantity=" + quantity + ", date=" + date + "]";
    }
}

