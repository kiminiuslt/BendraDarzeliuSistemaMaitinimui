package eu.kiminiuslt.bdsm.recipe.controllers;

import eu.kiminiuslt.bdsm.helpers.MessageService;
import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.recipe.service.RecipeService;
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
