package eu.kiminiuslt.bdsm.core.recipe.mapper;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.Recipe;
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

  public RecipeNamesDto recipeMapToRecipeNamesDto(Recipe recipe) {
    return RecipeNamesDto.builder().recipeName(recipe.getName()).uuid(recipe.getUuid()).build();
  }

  public RecipeNamesDto recipeDtoMapToRecipeNamesDto(RecipeDto recipeDto) {
    return RecipeNamesDto.builder()
        .recipeName(recipeDto.getRecipeName())
        .uuid(recipeDto.getUuid())
        .allCarbohydrates(recipeDto.getAllCarbohydrates())
        .allCarbohydratesLittleOnes(recipeDto.getAllCarbohydratesLittleOnes())
        .allEnergyValueKcal(recipeDto.getAllEnergyValueKcal())
        .allEnergyValueKcalLittleOnes(recipeDto.getAllEnergyValueKcalLittleOnes())
        .allFat(recipeDto.getAllFat())
        .allFatLittleOnes(recipeDto.getAllFatLittleOnes())
        .allProteins(recipeDto.getAllProteins())
        .allProteinsLittleOnes(recipeDto.getAllProteinsLittleOnes())
        .build();
  }
}
