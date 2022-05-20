package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import eu.kiminiuslt.bdsm.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeMapper recipeMapper;
  private final ProductService productService;
  private Set<ProductAndQuantityDto> temporaryList = new HashSet<>();

  public void addRecipe(RecipeDto recipeDto) {
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(recipeDto));
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable).map(recipeMapper::recipeMapToRecipeDto);
  }

  public RecipeDto getCreatedRecipe() {
    return RecipeDto.builder().productsList(temporaryList).build();
  }

  public void addProductToRecipe(ProductAndQuantityDto productAndQuantityDto) {
    productAndQuantityDto.setProduct(getProductByUUID(productAndQuantityDto.getProductUUID()));
    temporaryList.add(productAndQuantityDto);
  }

  public void deleteProductFromRecipe(UUID uuid) {
    temporaryList.remove(
        temporaryList.stream().filter(e -> uuid.equals(e.getProductUUID())).findAny().orElse(null));
  }

  public List<ProductForRecipeDto> getAllProducts() {
    return productService.getProductsListRecipeDto();
  }

  public Product getProductByUUID(UUID uuid) {
    return productService.getProductByUUID(uuid);
  }

  public RecipeDto getRecipeByUUID(UUID uuid) {
    return recipeMapper.recipeMapToRecipeDto(recipeRepository.findByUuid(uuid));
  }

  @Transactional
  public void updateRecipe(RecipeDto recipeDto) {
    recipeRepository.save(
        recipeRepository.findByUuid(recipeDto.getUuid()).toBuilder()
            .name(recipeDto.getRecipeName())
            .recipeText(recipeDto.getRecipeText())
            // TODO: FIX TO SAVE LIST OF PRODUCTS PASS A PRODUCT ID
            //            .productsList(recipeDto.getProductsList())
            .build());
  }

  @Transactional
  public void deleteRecipe(UUID uuid) {
    recipeRepository.deleteById(recipeRepository.findByUuid(uuid).getId());
  }
}
