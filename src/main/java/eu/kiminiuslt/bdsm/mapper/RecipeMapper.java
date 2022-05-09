package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Recipe;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecipeMapper {
  public RecipeDto recipeMapToRecipeDto(Recipe recipe) {
    return RecipeDto.builder()
        .id(recipe.getId())
        .uuid(recipe.getUuid())
        .recipeName(recipe.getName())
        .recipeText(recipe.getRecipeText())
        .products(recipe.getProducts())
        .build();
  }

  public Recipe recipeDtoMapToRecipe(RecipeDto recipeDto) {
    return Recipe.builder()
        .uuid(UUID.randomUUID())
        .name(recipeDto.getRecipeName())
        .recipeText(recipeDto.getRecipeText())
        .products(recipeDto.getProducts())
        .build();
  }
}
