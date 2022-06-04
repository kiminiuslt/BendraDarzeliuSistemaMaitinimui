package eu.kiminiuslt.bdsm.product.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForRecipeDto {
  private UUID uuid;
  private String name;
  private double proteins;
  private double fat;
  private double carbohydrates;
  private double energyValueKcal;
}
