package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.RecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
  public RecipeDto recipeMapToRecipeDto(Recipe recipe) {
    return RecipeDto.builder()
        .id(recipe.getId())
        .recipeName(recipe.getName())
        .recipeText(recipe.getRecipeText())
        .products(recipe.getProducts())
        .build();
  }

  public Recipe recipeDtoMapToRecipe(RecipeDto recipeDto) {
    return Recipe.builder()
        .id(recipeDto.getId())
        .name(recipeDto.getRecipeName())
        .recipeText(recipeDto.getRecipeText())
        .products(recipeDto.getProducts())
        .build();
  }
}
