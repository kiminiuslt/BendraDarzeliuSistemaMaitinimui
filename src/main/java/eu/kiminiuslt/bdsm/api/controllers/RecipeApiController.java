package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.api.commons.CrudApiDocumentation;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.NewRecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
@PreAuthorize("hasRole('DIETIST')")
@Api(tags = "Recipe Controller")
public class RecipeApiController implements CrudApiDocumentation<RecipeDto> {

  private final RecipeService recipeService;

  @Override
  @ApiOperation(value = "Save new recipe", httpMethod = "POST")
  public ResponseEntity<Void> create(RecipeDto object) {
    recipeService.addRecipe(object);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  @ApiOperation(
      value = "Get recipes page",
      notes = "Chunk of all recipes list implemented by pagination",
      httpMethod = "GET")
  public Page<RecipeDto> readPaginated(int page, int size) {
    return recipeService.getPageableRecipes(PageRequest.of(page, size));
  }

  @Override
  @ApiOperation(value = "Update recipe", httpMethod = "PUT", notes = "Updates only one recipe")
  public ResponseEntity<Void> update(RecipeDto object) {
    recipeService.updateRecipe(object);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  @ApiOperation(value = "Delete recipe", httpMethod = "DELETE")
  public ResponseEntity<Void> delete(UUID uuid) {
    recipeService.deleteRecipe(uuid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/newRecipe")
  @ApiOperation(
      value = "Get newRecipeDto",
      notes =
          "newRecipeDto contains empty recipeDto, empty ProductAndQuantityDto and all products list",
      httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered newRecipeDto"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  public NewRecipeDto newRecipe() {
    return recipeService.getNewRecipeDto();
  }

  @PostMapping("/newRecipe/addProduct")
  @ApiOperation(
      value = "Add Product and quantity to recipe",
      notes =
          "Returns newRecipeDto with added product and quantity. ProductAndQuantityDto will be empty ",
      httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully added product to recipe"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  public NewRecipeDto addProductToRecipe(@RequestBody NewRecipeDto newRecipeDto) {
    return recipeService.addProductAndQuantityToRecipe(newRecipeDto);
  }

  @PostMapping("/newRecipe/removeProduct")
  @ApiOperation(
          value = "Remove Product and quantity from recipe",
          notes =
                  "Returns newRecipeDto with removed product and quantity by uuid. ProductAndQuantityDto will be empty ",
          httpMethod = "POST")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Successfully removed product from recipe"),
                  @ApiResponse(code = 401, message = AUTHENTICATION),
                  @ApiResponse(code = 403, message = AUTHORIZATION)
          })
  public NewRecipeDto removeProductFromRecipe(@RequestBody NewRecipeDto newRecipeDto) {
    return recipeService.removeProductAndQuantityFromRecipe(newRecipeDto);
  }
}
