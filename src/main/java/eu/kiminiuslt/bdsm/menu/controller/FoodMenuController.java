package eu.kiminiuslt.bdsm.menu.controller;

import eu.kiminiuslt.bdsm.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import eu.kiminiuslt.bdsm.menu.service.MenuService;
import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food-menu")
public class FoodMenuController {
  private final MenuService menuService;

  @GetMapping
  public String foodMenu() {
//    MenuDto menu = MenuDto.builder().daysList(new ArrayList<>()).build();
//    menuService.save(menu);
    menuService.delete();
    return "/foodMenu/food-menu";
  }
}
