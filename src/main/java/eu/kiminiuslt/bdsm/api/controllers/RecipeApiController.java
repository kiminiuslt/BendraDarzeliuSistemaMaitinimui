package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.api.commons.CrudApiDocumentation;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
@PreAuthorize("hasRole('DIETIST')")
@Api(tags = "Recipe Controller")
public class RecipeApiController implements CrudApiDocumentation<RecipeDto> {

  private final RecipeService recipeService;

  @Override
  @ApiOperation(value = "Create recipe", httpMethod = "POST")
  public ResponseEntity<Void> create(RecipeDto object) {
    recipeService.addRecipe(); // TODO: FIX SAVING IN SERVICE BY PASSING RecipeDto
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
}
