package pe.edu.upc.sellmaster.order_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "monto_total", nullable = false) // Aquí se asegura que el nombre en la base de datos sea "monto_total"
    private double montoTotal;

    @Column(nullable = false)
    private long clientID;

    @Column(nullable = false)
    private long userID;
}

