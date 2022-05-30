package eu.kiminiuslt.bdsm.menu.service;

import eu.kiminiuslt.bdsm.menu.mapper.MenuDayMapper;
import eu.kiminiuslt.bdsm.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.menu.repository.MenuDayRepository;
import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
  private final MenuDayRepository menuDayRepository;
  private final MenuDayMapper menuDayMapper;
  private final RecipeService recipeService;

  public MenuDto getMenu() {
    return MenuDto.builder().daysList(getAllDayList()).build();
  }

  private List<MenuDayDto> getAllDayList() {
    return menuDayRepository.findAll().stream()
        .map(menuDayMapper::entityToDto)
        .collect(Collectors.toList());
  }

  public MenuDayDto getMenuDayByID(int id) {
    return menuDayMapper.entityToDto(menuDayRepository.getById(id));
  }

  public List<RecipeDto> getAllRecipesList() {
    return recipeService.getAllRecipes();
  }

  public void addRecipeToDay(Integer id, String recipeUUID) {
    menuDayRepository.addDayRecipeToTable(id, getRecipeIdByUUID(UUID.fromString(recipeUUID)));
  }

  public void removeRecipeFromDay(Integer dayId, UUID uuid) {
    menuDayRepository.removeDayRecipeFromTable(dayId, getRecipeIdByUUID(uuid));
  }

  private Integer getRecipeIdByUUID(UUID uuid) {
    return recipeService.getRecipeDtoByUUID(uuid).getId();
  }

  public List<RecipeDto> getFiltredRecipesList(Set<RecipeDto> dayRecipesDto) {
    if (dayRecipesDto.size() == 0) {
      return getAllRecipesList();
    }
    List<RecipeDto> dayRecipes = new ArrayList<>(dayRecipesDto);
    List<RecipeDto> result = getAllRecipesList();
    for (int i = 0; i < dayRecipesDto.size(); i++) {
      int id = dayRecipes.get(i).getId();
      result.removeIf(recipeDto -> recipeDto.getId() == id);
    }
    return result;
  }
}
