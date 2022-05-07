package eu.kiminiuslt.bdsm.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecipeDto {
  private Integer id;
  private String recipeName;
  private String recipeText;
  private String products;
}