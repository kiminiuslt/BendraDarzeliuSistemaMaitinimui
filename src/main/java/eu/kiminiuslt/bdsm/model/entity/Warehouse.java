package eu.kiminiuslt.bdsm.model.entity;

import eu.kiminiuslt.bdsm.model.enums.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse", schema = "bdsm")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID uuid;

    @Column(name = "product_id")
    private int productId;
    private double amount;
    private String invoice;

    @Column(name = "product_type")
    private ProductType productType;
}
