package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeMapper recipeMapper;
  private final ProductService productService;

  public void addRecipe(RecipeDto recipeDto) {

    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(recipeDto));
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable).map(recipeMapper::recipeMapToRecipeDto);
  }

  public List<ProductForRecipeDto> getAllProducts(){
    return productService.getProductsList();
  }

}
