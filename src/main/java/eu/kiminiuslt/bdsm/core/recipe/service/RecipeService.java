package eu.kiminiuslt.bdsm.core.recipe.service;

import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.core.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.NewRecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import eu.kiminiuslt.bdsm.jpa.repository.RecipeRepository;
import eu.kiminiuslt.bdsm.core.product.service.ProductService;
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

  public void addRecipe(RecipeDto recipeDto) {
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(recipeDto));
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository
        .findAll(pageable)
        .map(recipeMapper::recipeMapToRecipeDto)
        .map(recipeCalculationsService::sumOfMainMaterials);
  }

  public List<ProductsNamesDto> getAllProducts() {
    return productService.getProductsListRecipeDto();
  }

  public RecipeDto getRecipeDtoByUUID(UUID uuid) {
    return recipeMapper.recipeMapToRecipeDto(recipeRepository.findByUuid(uuid));
  }

  @Transactional
  public void updateRecipe(RecipeDto recipeDto) {
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(recipeDto));
    //    FIXME: UPDATING RECIPE TABLE "PRODUCT_AND_QUANTITY" DOES NOT UPDATES
  }

  @Transactional
  public void deleteRecipe(UUID uuid) {
    recipeRepository.deleteById(recipeRepository.findByUuid(uuid).getId());
  }

  public List<RecipeNamesDto> getAllRecipes() {
    return recipeRepository.findAll().stream()
        .map(recipeMapper::recipeMapToRecipeDto)
        .map(recipeCalculationsService::sumOfMainMaterials)
        .map(recipeMapper::recipeDtoMapToRecipeNamesDto)
        .sorted(Comparator.comparing(RecipeNamesDto::getRecipeName))
        .collect(Collectors.toList());
  }

  public NewRecipeDto getNewRecipeDto() {
    return NewRecipeDto.builder()
        .recipeDto(RecipeDto.builder().productsList(new HashSet<>()).build())
        .listOfProducts(getAllProducts())
        .productAndQuantityDto(ProductAndQuantityDto.builder().build())
        .build();
  }

  public NewRecipeDto addProductAndQuantityToRecipe(NewRecipeDto newRecipeDto) {
    ProductAndQuantityDto productAndQuantityDto = newRecipeDto.getProductAndQuantityDto();
    Product product = productService.getProductByUUID(productAndQuantityDto.getProductUUID());

    productAndQuantityDto.setProduct(product);
    newRecipeDto.getRecipeDto().getProductsList().add(productAndQuantityDto);
    return calculationsAndReset(newRecipeDto);
  }

  public NewRecipeDto removeProductAndQuantityFromRecipe(NewRecipeDto newRecipeDto) {
    UUID uuid = newRecipeDto.getProductAndQuantityDto().getProductUUID();
    newRecipeDto.getRecipeDto().getProductsList().removeIf(o -> o.getProductUUID().equals(uuid));
    return calculationsAndReset(newRecipeDto);
  }

  private NewRecipeDto calculationsAndReset(NewRecipeDto newRecipeDto) {
    newRecipeDto.setRecipeDto(
        recipeCalculationsService.sumOfMainMaterials(newRecipeDto.getRecipeDto()));
    newRecipeDto.setProductAndQuantityDto(ProductAndQuantityDto.builder().build());
    return newRecipeDto;
  }
}
