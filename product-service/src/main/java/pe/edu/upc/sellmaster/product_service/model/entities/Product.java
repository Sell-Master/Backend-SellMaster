package pe.edu.upc.sellmaster.product_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;

    @Column(name = "product_type", nullable = false, length = 100)
    private String productType;

    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "price", nullable = false)
    private double price;

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", productType=" + productType + ", brand=" + brand + ", additionalInfo=" + additionalInfo + ", price=" + price + "]";
    }
}

