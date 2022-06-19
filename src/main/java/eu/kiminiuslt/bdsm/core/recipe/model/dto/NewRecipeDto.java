package eu.kiminiuslt.bdsm.core.recipe.model.dto;

import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRecipeDto {
  private RecipeDto recipeDto;
  private ProductAndQuantityDto productAndQuantityDto;
  private List<ProductsNamesDto> listOfProducts;
}
