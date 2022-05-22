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
    if (this.temporaryRecipeDto == null) {
      recipeRepository.save(
          recipeMapper.recipeDtoMapToRecipe(
              RecipeDto.builder()
                  .recipeName(this.temporaryName)
                  .recipeText(this.temporaryText)
                  .productsList(this.temporaryList)
                  .build()));
    } else {
      this.temporaryRecipeDto.setRecipeName(this.temporaryName);
      this.temporaryRecipeDto.setRecipeText(this.temporaryText);
      this.temporaryRecipeDto.setProductsList(this.temporaryList);
      recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(this.temporaryRecipeDto));
    }
    resetRecipeDto();
  }

  public Page<RecipeDto> getPageableRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable).map(recipeMapper::recipeMapToRecipeDto);
  }

  public void saveNameAndText(RecipeDto recipeDto) {
    this.temporaryName = recipeDto.getRecipeName();
    this.temporaryText = recipeDto.getRecipeText();
  }

  public RecipeDto getCreatedRecipe() {
    if (this.temporaryRecipeDto == null) {
      return RecipeDto.builder().productsList(temporaryList).build();
    }
    return this.temporaryRecipeDto;
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

  public RecipeDto getRecipeDtoByUUID(UUID uuid) {
    return recipeMapper.recipeMapToRecipeDto(recipeRepository.findByUuid(uuid));
  }

  public RecipeDto updateRecipe(UUID uuid) {
    if (this.temporaryRecipeDto == null) {
      this.temporaryRecipeDto = getRecipeDtoByUUID(uuid);
      temporaryName = temporaryRecipeDto.getRecipeName();
      temporaryText = temporaryRecipeDto.getRecipeText();
      temporaryList = temporaryRecipeDto.getProductsList();
      return this.temporaryRecipeDto;
    }
    return this.temporaryRecipeDto;
  }

  @Transactional
  public void updateRecipe(RecipeDto recipeDto) {
    this.temporaryRecipeDto.setRecipeName(recipeDto.getRecipeName());
    this.temporaryRecipeDto.setRecipeText(recipeDto.getRecipeText());
    this.temporaryRecipeDto.getProductsList().addAll(temporaryList);
    recipeRepository.save(recipeMapper.recipeDtoMapToRecipe(this.temporaryRecipeDto));
    resetRecipeDto();
  }

  @Transactional
  public void deleteRecipe(UUID uuid) {
    recipeRepository.deleteById(recipeRepository.findByUuid(uuid).getId());
  }

  public void resetRecipeDto() {
    this.temporaryName = "";
    this.temporaryText = "";
    this.temporaryList = new HashSet<>();
    this.temporaryRecipeDto = null;
  }
}
