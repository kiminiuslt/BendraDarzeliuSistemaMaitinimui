package eu.kiminiuslt.bdsm.menu.mapper;

import eu.kiminiuslt.bdsm.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import eu.kiminiuslt.bdsm.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MenuDayMapper {
  private final RecipeMapper recipeMapper;

  public MenuDayDto entityToDto(MenuDay entity) {
    return MenuDayDto.builder()
        .id(entity.getId())
        .dayNumber(entity.getDayNumber())
        .dayRecipesDto(getRecipeDtoSet(entity.getDayRecipes()))
        .build();
  }

  private Set<RecipeDto> getRecipeDtoSet(Set<Recipe> dayRecipes) {
    return dayRecipes.stream().map(recipeMapper::recipeMapToRecipeDto).collect(Collectors.toSet());
  }
}
