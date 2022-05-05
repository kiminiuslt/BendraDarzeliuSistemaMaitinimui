package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipesController {

  private final RecipeService recipeService;

  @GetMapping
  public String recipesAll(
      Model model,
      @PageableDefault(
              size = 10,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    model.addAttribute("recipesListPages", recipeService.getAllRecipes(pageable));
    return "recipe/recipe_all";
  }
}
