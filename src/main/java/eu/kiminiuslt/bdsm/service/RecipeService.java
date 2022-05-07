package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.model.RecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Recipe;
import eu.kiminiuslt.bdsm.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public void addRecipe(RecipeDto recipeDto) {

    recipeRepository.save(
        Recipe.builder()
            .name(recipeDto.getRecipeName())
            .recipeText(recipeDto.getRecipeText())
            .products(recipeDto.getProducts())
            .build());
  }
}
