package eu.kiminiuslt.bdsm.recipe.service;

import eu.kiminiuslt.bdsm.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.product.model.entity.Product;
import eu.kiminiuslt.bdsm.recipe.repository.RecipeRepository;
import eu.kiminiuslt.bdsm.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeMapper recipeMapper;
  private final ProductService productService;
  private final RecipeCalculationsService recipeCalculationsService;
  private RecipeDto temporaryRecipeDto;

  public void addRecipe() {
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(temporaryRecipeDto));
    resetRecipeDto();
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository
        .findAll(pageable)
        .map(recipeMapper::recipeMapToRecipeDto)
        .map(recipeCalculationsService::sumOfMainMaterials);
  }

  public void saveNameAndText(RecipeDto recipeDto) {
    temporaryRecipeDto.setRecipeName(recipeDto.getRecipeName());
    temporaryRecipeDto.setRecipeText(recipeDto.getRecipeText());
  }

  public RecipeDto getCreatedRecipe() {
    if (temporaryRecipeDto == null) {
      temporaryRecipeDto = RecipeDto.builder().productsList(new HashSet<>()).build();
    }
    temporaryRecipeDto = recipeCalculationsService.sumOfMainMaterials(temporaryRecipeDto);
    return temporaryRecipeDto;
  }

  public void addProductToRecipe(ProductAndQuantityDto productAndQuantityDto) {
    productAndQuantityDto.setProduct(getProductByUUID(productAndQuantityDto.getProductUUID()));
    temporaryRecipeDto.getProductsList().add(productAndQuantityDto);
  }

  public void deleteProductFromRecipe(UUID uuid) {
    temporaryRecipeDto
        .getProductsList()
        .remove(
            temporaryRecipeDto.getProductsList().stream()
                .filter(e -> uuid.equals(e.getProductUUID()))
                .findAny()
                .orElse(null));
  }

  public List<ProductForRecipeDto> getAllProducts() {
    return productService.getProductsListRecipeDto();
  }

  public Product getProductByUUID(UUID uuid) {
    return productService.getProductByUUID(uuid);
  }

  public RecipeDto getRecipeDtoByUUID(UUID uuid) {
    return recipeMapper.recipeMapToRecipeDto(recipeRepository.findByUuid(uuid));
  }

  public RecipeDto getUpdateRecipe(UUID uuid) {
    if (temporaryRecipeDto == null) {
      temporaryRecipeDto = getRecipeDtoByUUID(uuid);
    }
    return temporaryRecipeDto;
  }

  @Transactional
  public void updateRecipe(RecipeDto recipeDto) {
    saveNameAndText(recipeDto);
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(temporaryRecipeDto));
    //    FIXME: UPDATING RECIPE TABLE "PRODUCT_AND_QUANTITY" DOES NOT UPDATES
    resetRecipeDto();
  }

  @Transactional
  public void deleteRecipe(UUID uuid) {
    recipeRepository.deleteById(recipeRepository.findByUuid(uuid).getId());
  }

  public void resetRecipeDto() {
    temporaryRecipeDto = null;
  }

  public List<RecipeDto> getAllRecipes() {
    return recipeRepository.findAll().stream()
        .map(recipeMapper::recipeMapToRecipeDto)
        .sorted(Comparator.comparing(RecipeDto::getRecipeName))
        .collect(Collectors.toList());
  }
}
