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
  private String temporaryName = "";
  private String temporaryText = "";
  private RecipeDto temporaryRecipeDto;

  public void addRecipe() {
    recipeRepository.save(
        recipeMapper.recipeDtoMapToRecipe(
            RecipeDto.builder()
                .recipeName(this.temporaryName)
                .recipeText(this.temporaryText)
                .productsList(this.temporaryList)
                .build()));
    this.temporaryName = "";
    this.temporaryText = "";
    this.temporaryList = new HashSet<>();
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable).map(recipeMapper::recipeMapToRecipeDto);
  }

  public void saveNameAndText(RecipeDto recipeDto) {
    this.temporaryName = recipeDto.getRecipeName();
    this.temporaryText = recipeDto.getRecipeText();
  }

  public RecipeDto getCreatedRecipe() {
    return RecipeDto.builder().productsList(temporaryList).build();
  }

  public void addProductToRecipe(ProductAndQuantityDto productAndQuantityDto) {
    // TODO: BUG FIX. WHEN ADD A PRODUCT, RECIPE TEXT AND NAME ARE GONE. ALSO RecipeDto ID, UUID ARE NULL
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

  public RecipeDto updateRecipe(UUID uuid) {
    this.temporaryRecipeDto = getRecipeByUUID(uuid);
    this.temporaryList = this.temporaryRecipeDto.getProductsList();
    return this.temporaryRecipeDto;
  }

  @Transactional
  public void updateRecipe(RecipeDto recipeDto) {
    this.temporaryRecipeDto.setRecipeName(recipeDto.getRecipeName());
    this.temporaryRecipeDto.setRecipeText(recipeDto.getRecipeText());
    this.temporaryRecipeDto.getProductsList().addAll(temporaryList);
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(this.temporaryRecipeDto));
    this.temporaryRecipeDto = null;
  }

  @Transactional
  public void deleteRecipe(UUID uuid) {
    recipeRepository.deleteById(recipeRepository.findByUuid(uuid).getId());
  }
}
