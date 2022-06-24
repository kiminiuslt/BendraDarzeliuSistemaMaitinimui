package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.menu.model.dto.DayShortageDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import eu.kiminiuslt.bdsm.core.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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
  public MenuDayDto addRecipeIntoDay(Integer id, UUID recipeUUID) {
    menuService.addRecipeToDay(id, recipeUUID);
    return menuService.getMenuDayByID(id);
  }

  @Override
  public MenuDayDto removeRecipeFromDay(Integer dayId, UUID recipeUUID) {
    menuService.removeRecipeFromDay(dayId, recipeUUID);
    return menuService.getMenuDayByID(dayId);
  }

  @Override
  public List<DayShortageDto> calculateShortage(List<PeopleCountDto> peopleCountDtoList) {
    return menuService.calculateMenuShortage(peopleCountDtoList);
  }
}
