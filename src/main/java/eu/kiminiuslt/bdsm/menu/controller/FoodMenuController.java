package eu.kiminiuslt.bdsm.menu.controller;

import eu.kiminiuslt.bdsm.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
