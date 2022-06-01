package eu.kiminiuslt.bdsm.menu.mapper;

import eu.kiminiuslt.bdsm.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import eu.kiminiuslt.bdsm.menu.service.MenuCalculationsService;
import eu.kiminiuslt.bdsm.menu.service.ProductsShortageService;
import eu.kiminiuslt.bdsm.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MenuDayMapper {
  private final RecipeMapper recipeMapper;
  private final MenuCalculationsService menuCalculationsService;

  public MenuDayDto entityToDto(MenuDay entity) {
    MenuDayDto menuDayDto =
        MenuDayDto.builder()
            .id(entity.getId())
            .dayNumber(entity.getDayNumber())
            .dayRecipesDto(getRecipeDtoSet(entity.getDayRecipes()))
            .productShortage(new ArrayList<>())
            .build();
    menuDayDto.setDayEnergyValue(
        menuCalculationsService.getDayEnergyValue(menuDayDto.getDayRecipesDto()));
    return menuDayDto;
  }

  private Set<RecipeDto> getRecipeDtoSet(Set<Recipe> dayRecipes) {
    return dayRecipes.stream().map(recipeMapper::recipeMapToRecipeDto).collect(Collectors.toSet());
  }
}
