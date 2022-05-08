package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.helpers.MessageService;
import eu.kiminiuslt.bdsm.model.RecipeDto;
import eu.kiminiuslt.bdsm.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    model.addAttribute("recipeDto", RecipeDto.builder().build());
    model.addAttribute("message", messageService.getMessage(message));

    return "/recipe/recipe-form";
  }

  @PostMapping("/recipeForm")
  public String saveRecipe(RecipeDto recipeDto) {
    recipeService.addRecipe(recipeDto);
    return "redirect:/recipes/recipeForm?message=recipe.create.successes";
  }
}
