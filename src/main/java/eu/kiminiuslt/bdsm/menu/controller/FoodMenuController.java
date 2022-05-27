package eu.kiminiuslt.bdsm.menu.controller;

import eu.kiminiuslt.bdsm.menu.service.MenuService;
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
@RequestMapping("/food-menu")
public class FoodMenuController {
  private final MenuService menuService;

  @GetMapping
  public String foodMenu(Model model) {
    model.addAttribute("menu", menuService.getMenu());
    return "/foodMenu/food-menu";
  }

  @GetMapping("/{id}/update")
  public String getUpdate(Model model, @PathVariable("id") int id) {
    model.addAttribute("menuDay", menuService.getMenuDayByID(id));
    model.addAttribute("recipesList", menuService.getAllRecipesList());
    return "/foodMenu/edit-day";
  }

  @PostMapping("/{id}/update")
  public String recipeToDay(Model model, @PathVariable("id") Integer id, String recipeUUID) {
    menuService.addRecipeToDay(id, recipeUUID);
    return "redirect:/food-menu/" + id + "/update";
  }

  @GetMapping("/{id}/{recipeUUID}/removeRecipe")
  public String removeRecipeFromDay(
      @PathVariable("id") Integer dayId, @PathVariable("recipeUUID") UUID uuid) {
    menuService.removeRecipeFromDay(dayId, uuid);
    return "redirect:/food-menu/" + dayId + "/update";
  }
}
