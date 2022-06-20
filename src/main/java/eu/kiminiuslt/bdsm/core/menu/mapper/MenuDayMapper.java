package eu.kiminiuslt.bdsm.core.menu.mapper;

import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.service.MenuCalculationsService;
import eu.kiminiuslt.bdsm.jpa.entity.MenuDay;
import eu.kiminiuslt.bdsm.core.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.jpa.entity.Recipe;
import eu.kiminiuslt.bdsm.core.recipe.service.RecipeCalculationsService;
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
  private final RecipeCalculationsService recipeCalculationsService;

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
    menuDayDto.setDayEnergyValueLittleOnes(
        menuCalculationsService.getDayEnergyValueLittleOnes(menuDayDto.getDayRecipesDto()));
    return menuDayDto;
  }

  private Set<RecipeDto> getRecipeDtoSet(Set<Recipe> dayRecipes) {
    return dayRecipes.stream()
        .map(recipeMapper::recipeMapToRecipeDto)
        .map(recipeCalculationsService::sumOfMainMaterials)
        .collect(Collectors.toSet());
  }
}
