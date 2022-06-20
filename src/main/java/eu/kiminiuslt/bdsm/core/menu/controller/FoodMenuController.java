package eu.kiminiuslt.bdsm.core.menu.controller;

import eu.kiminiuslt.bdsm.core.menu.service.MenuService;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIETIST')")
@RequestMapping("/food-menu")
public class FoodMenuController {
  private final MenuService menuService;

  @GetMapping
  public String foodMenu(
      Model model, @ModelAttribute("passedPeopleCount") PeopleCountDto peopleCountDto) {
//    model.addAttribute("menu", menuService.getMenu(peopleCountDto));
    model.addAttribute("peopleCount", menuService.getPeopleCount());
    return "/foodMenu/food-menu";
  }

  @PostMapping
  public String foodMenu(RedirectAttributes redirectAttributes, PeopleCountDto peopleCountDto) {
    redirectAttributes.addFlashAttribute("passedPeopleCount", peopleCountDto);
    return "redirect:/food-menu";
  }

  @GetMapping("/{id}/update")
  public String getUpdate(Model model, @PathVariable("id") int id) {
    MenuDayDto menuDayDto = menuService.getMenuDayByID(id);
    model.addAttribute(
        "recipesList", menuService.getFilteredRecipesList(menuDayDto.getDayRecipesDto()));
    model.addAttribute("menuDay", menuDayDto);
    return "/foodMenu/edit-day";
  }

  @PostMapping("/{id}/update")
  public String recipeToDay(@PathVariable("id") Integer id, String recipeUUID) {
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
