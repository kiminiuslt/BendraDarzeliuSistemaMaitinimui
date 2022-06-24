package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.mapper.MenuDayMapper;
import eu.kiminiuslt.bdsm.core.menu.model.dto.*;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeNamesDto;
import eu.kiminiuslt.bdsm.jpa.repository.MenuDayRepository;
import eu.kiminiuslt.bdsm.core.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
  private final MenuDayRepository menuDayRepository;
  private final MenuDayMapper menuDayMapper;
  private final RecipeService recipeService;
  private final ProductsShortageService productsShortageService;

  public MenuDto getMenu() {
    return MenuDto.builder().daysList(getAllDayList()).build();
  }

  public List<DayShortageDto> calculateMenuShortage(List<PeopleCountDto> peopleCountDto) {
    MenuDto menuDto = MenuDto.builder().daysList(getAllDayList()).build();
    return productsShortageService.getProductsShortageList(menuDto, peopleCountDto);
  }

  private List<MenuDayDto> getAllDayList() {
    return menuDayRepository.findAll().stream()
        .map(menuDayMapper::entityToDto)
        .collect(Collectors.toList());
  }

  public MenuDayDto getMenuDayByID(int id) {
    MenuDayDto menuDayDto = menuDayMapper.entityToDto(menuDayRepository.getById(id));
    menuDayDto.setAllRecipesList(getFilteredRecipesList(menuDayDto.getDayRecipesNamesDto()));
    return menuDayDto;
  }

  public List<RecipeNamesDto> getAllRecipesList() {
    return recipeService.getAllRecipes();
  }

  public void addRecipeToDay(Integer id, UUID recipeUUID) {
    menuDayRepository.addDayRecipeToTable(id, getRecipeIdByUUID(recipeUUID));
  }

  public void removeRecipeFromDay(Integer dayId, UUID uuid) {
    menuDayRepository.removeDayRecipeFromTable(dayId, getRecipeIdByUUID(uuid));
  }

  private Integer getRecipeIdByUUID(UUID uuid) {
    return recipeService.getRecipeDtoByUUID(uuid).getId();
  }

  public List<RecipeNamesDto> getFilteredRecipesList(Set<RecipeNamesDto> dayRecipesDto) {
    if (dayRecipesDto.size() == 0) {
      return getAllRecipesList();
    }
    List<RecipeNamesDto> dayRecipes = new ArrayList<>(dayRecipesDto);
    List<RecipeNamesDto> result = getAllRecipesList();
    for (int i = 0; i < dayRecipesDto.size(); i++) {
      UUID uuid = dayRecipes.get(i).getUuid();
      result.removeIf(recipeDto -> recipeDto.getUuid() == uuid);
    }
    return result;
  }
}
