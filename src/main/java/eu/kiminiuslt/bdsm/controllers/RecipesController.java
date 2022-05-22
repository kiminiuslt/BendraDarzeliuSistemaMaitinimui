package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.helpers.MessageService;
import eu.kiminiuslt.bdsm.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipesController {

  private final RecipeService recipeService;
  private final MessageService messageService;

  @GetMapping
  public String recipesAll(
      Model model,
      @PageableDefault(
              size = 5,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    model.addAttribute("recipeListPages", recipeService.getPageableRecipes(pageable));
    return "recipe/recipe-all";
  }

  @GetMapping("/recipeForm")
  public String newRecipe(Model model, String message) {
    model.addAttribute("recipeDto", recipeService.getCreatedRecipe());
    model.addAttribute("message", messageService.getMessage(message));

    return "/recipe/recipe-form";
  }

  @PostMapping("/recipeForm")
  public String saveRecipe(RecipeDto recipeDto) {
    recipeService.saveNameAndText(recipeDto);
    recipeService.addRecipe();
    return "redirect:/recipes/recipeForm?message=recipe.create.successes";
  }

  @GetMapping("/{uuid}/deleteProduct")
  public String deleteProduct(@PathVariable("uuid") UUID uuid) {
    recipeService.deleteProductFromRecipe(uuid);
    return "redirect:/recipes/recipeForm";
  }

  @GetMapping("/{uuid1}/{uuid}/deleteProduct")
  public String editingDeleteProduct(
      @PathVariable("uuid") UUID uuid, @PathVariable("uuid1") UUID uuid1) {
    recipeService.deleteProductFromRecipe(uuid);
    return "redirect:/recipes/" + uuid1 + "/update";
  }

  @GetMapping("/addProduct")
  public String addProduct(Model model) {
    model.addAttribute("allProducts", recipeService.getAllProducts());
    model.addAttribute("productAndQuantityDto", ProductAndQuantityDto.builder().build());
    return "recipe/add-product";
  }

  @PostMapping("/addProduct")
  public String addProduct(ProductAndQuantityDto productAndQuantityDto) {
    recipeService.addProductToRecipe(productAndQuantityDto);
    return "redirect:/recipes/recipeForm";
  }

  @GetMapping("/{uuid}/addProduct")
  public String editRecipeAddProduct(Model model, @PathVariable("uuid") UUID uuid) {
    model.addAttribute("allProducts", recipeService.getAllProducts());
    model.addAttribute("productAndQuantityDto", ProductAndQuantityDto.builder().build());
    model.addAttribute("recipeUUID", uuid);
    return "recipe/add-product";
  }

  @PostMapping("/{uuid}/addProduct")
  public String addProduct(
      ProductAndQuantityDto productAndQuantityDto, @PathVariable("uuid") UUID uuid) {
    recipeService.addProductToRecipe(productAndQuantityDto);
    return "redirect:/recipes/" + uuid + "/update";
  }

  @GetMapping("/{uuid}/update")
  public String getUpdateRecipe(Model model, @PathVariable("uuid") UUID uuid) {
    model.addAttribute("recipeDto", recipeService.updateRecipe(uuid));
    return "/recipe/recipe-form";
  }

  @PostMapping("/{uuid}/update")
  public String updateRecipe(
      Model model,
      RecipeDto recipeDto,
      @PageableDefault(
              size = 5,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    recipeService.updateRecipe(recipeDto);
    model.addAttribute("recipeListPages", recipeService.getPageableRecipes(pageable));
    model.addAttribute("message", "message");
    return "recipe/recipe-all";
  }

  @GetMapping("/{uuid}/delete")
  public String deleteRecipe(
      Model model,
      @PathVariable("uuid") UUID uuid,
      @PageableDefault(
              size = 5,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    recipeService.deleteRecipe(uuid);
    model.addAttribute("recipeListPages", recipeService.getPageableRecipes(pageable));
    return "recipe/recipe-all";
  }
}
