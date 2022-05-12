package eu.kiminiuslt.bdsm.model.dto;

import eu.kiminiuslt.bdsm.model.entity.Product;
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
  private Set<Product> productsList;
}
