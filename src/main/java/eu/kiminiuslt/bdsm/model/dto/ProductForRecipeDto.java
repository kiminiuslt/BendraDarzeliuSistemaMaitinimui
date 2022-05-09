package eu.kiminiuslt.bdsm.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductForRecipeDto {
  private Integer id;
  private String name;
  private double proteins;
  private double fat;
  private double carbohydrates;
  private double energyValueKcal;
}
