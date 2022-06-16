package eu.kiminiuslt.bdsm.core.recipe.model.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecipeDto {
  private Integer id;
  private UUID uuid;
  private String recipeName;
  private String recipeText;
  private Double allProteins;
  private Double allFat;
  private Double allCarbohydrates;
  private Double allEnergyValueKcal;
  private Set<ProductAndQuantityDto> productsList;
}
