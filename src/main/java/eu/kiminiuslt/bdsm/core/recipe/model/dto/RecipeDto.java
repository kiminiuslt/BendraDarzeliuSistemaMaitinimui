package eu.kiminiuslt.bdsm.core.recipe.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RecipeDto extends RecipeNamesDto {
  private Integer id;
  private String recipeText;
  private Set<ProductAndQuantityDto> productsList;
}
