package eu.kiminiuslt.bdsm.core.menu.mapper;

import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.service.MenuCalculationsService;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.MenuDay;
import eu.kiminiuslt.bdsm.core.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.jpa.entity.Recipe;
import eu.kiminiuslt.bdsm.core.recipe.service.RecipeCalculationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
            .dayRecipesNamesDto(getRecipeDtoSet(entity.getDayRecipes()))
            .build();
    menuDayDto.setDayEnergyValue(
        menuCalculationsService.getDayEnergyValue(menuDayDto.getDayRecipesNamesDto()));
    menuDayDto.setDayEnergyValueLittleOnes(
        menuCalculationsService.getDayEnergyValueLittleOnes(menuDayDto.getDayRecipesNamesDto()));
    return menuDayDto;
  }

  private Set<RecipeNamesDto> getRecipeDtoSet(Set<Recipe> dayRecipes) {
    return dayRecipes.stream()
        .map(recipeMapper::recipeMapToRecipeDto)
        .map(recipeCalculationsService::sumOfMainMaterials)
        .map(recipeMapper::recipeDtoMapToRecipeNamesDto)
        .collect(Collectors.toSet());
  }
}
