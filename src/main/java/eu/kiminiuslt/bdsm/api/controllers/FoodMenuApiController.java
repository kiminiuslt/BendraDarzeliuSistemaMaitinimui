package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.core.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIETIST')")
public class FoodMenuApiController implements FoodMenuApiDocumentation {
  private final MenuService menuService;

  @Override
  public MenuDto getMenu() {
    return menuService.getMenu();
  }

  @Override
  public MenuDayDto getDayMenu(Integer id) {
    return menuService.getMenuDayByID(id);
  }

  @Override
  public MenuDayDto addRecipeIntoDay(Integer id, String recipeUUID) {
    menuService.addRecipeToDay(id, recipeUUID);
    return menuService.getMenuDayByID(id);
  }
}
