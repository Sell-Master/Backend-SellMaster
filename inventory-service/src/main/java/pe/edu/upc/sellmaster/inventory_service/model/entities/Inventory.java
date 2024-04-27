package pe.edu.upc.sellmaster.inventory_service.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryID;

    @Column(nullable = false)
    private long productID; // Store as long instead of Product entity

    @Column(nullable = false)
    private long userID; // Store as long instead of User entity

    @Column(nullable = false)
    private int stock;

    @Override
    public String toString() {
        return "Inventory [inventoryID=" + inventoryID + ", productID=" + productID + ", userID=" + userID + ", stock=" + stock + "]";
    }
}


