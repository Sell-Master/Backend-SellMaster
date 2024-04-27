package pe.edu.upc.sellmaster.order_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailID;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private long productID;

    @Column(nullable = false)
    private long orderID;
}

