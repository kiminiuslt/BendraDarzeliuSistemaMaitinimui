package eu.kiminiuslt.bdsm.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_and_quantity", schema = "bdsm", catalog = "postgres")
public class ProductAndQuantity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "product_id")
  private Integer productId;

  @Column(name = "quantity_gross")
  private Double quantityGross;

  @Column(name = "quantity_net")
  private Double quantityNet;

  @Column(name = "quantity_gross_little_ones")
  private Double quantityGrossLittleOnes;

  @Column(name = "quantity_net_little_ones")
  private Double quantityNetLittleOnes;
}
