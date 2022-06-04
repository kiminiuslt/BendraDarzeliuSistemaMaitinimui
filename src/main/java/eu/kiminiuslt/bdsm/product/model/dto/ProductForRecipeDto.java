package eu.kiminiuslt.bdsm.product.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductForRecipeDto extends ProductsNamesDto {
  private double proteins;
  private double fat;
  private double carbohydrates;
  private double energyValueKcal;
}
