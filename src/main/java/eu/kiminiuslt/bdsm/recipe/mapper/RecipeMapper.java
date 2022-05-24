package eu.kiminiuslt.bdsm.recipe.mapper;

import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecipeMapper {

  private final ProductAndQuantityMapper productAndQuantityMapper;

  public RecipeDto recipeMapToRecipeDto(Recipe recipe) {
    return RecipeDto.builder()
        .id(recipe.getId())
        .uuid(recipe.getUuid())
        .recipeName(recipe.getName())
        .recipeText(recipe.getRecipeText())
        .productsList(productAndQuantityMapper.entityToDto(recipe.getProductsList()))
        .build();
  }

  public Recipe recipeDtoMapToRecipe(RecipeDto recipeDto) {
    if (recipeDto.getId() == null) {
      return Recipe.builder()
          .uuid(UUID.randomUUID())
          .name(recipeDto.getRecipeName())
          .recipeText(recipeDto.getRecipeText())
          .productsList(productAndQuantityMapper.dtoToEntity(recipeDto.getProductsList()))
          .build();
    }
    return Recipe.builder()
        .id(recipeDto.getId())
        .uuid(recipeDto.getUuid())
        .name(recipeDto.getRecipeName())
        .recipeText(recipeDto.getRecipeText())
        .productsList(productAndQuantityMapper.dtoToEntity(recipeDto.getProductsList()))
        .build();
  }
}