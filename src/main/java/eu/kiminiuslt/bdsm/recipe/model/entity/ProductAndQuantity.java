package eu.kiminiuslt.bdsm.recipe.model.entity;

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

  private double quantity;
}
