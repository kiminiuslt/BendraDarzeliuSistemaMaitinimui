package eu.kiminiuslt.bdsm.recipe.controllers;

import eu.kiminiuslt.bdsm.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIETIST')")
@RequestMapping("/recipes")
public class AddProductController {

  private final RecipeService recipeService;

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
}
