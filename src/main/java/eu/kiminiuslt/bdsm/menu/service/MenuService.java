package eu.kiminiuslt.bdsm.menu.service;

import eu.kiminiuslt.bdsm.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import eu.kiminiuslt.bdsm.menu.repository.MenuDayRepository;
import eu.kiminiuslt.bdsm.recipe.mapper.RecipeMapper;
import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import eu.kiminiuslt.bdsm.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {
   private final MenuDayRepository menuDayRepository;
   private final RecipeService recipeService;
   private final RecipeMapper recipeMapper;

   public void save(MenuDto menu) {
      menu.getDaysList().add(MenuDay.builder()
                      .dayNumber(1)
                      .dayRecipes(getSet())
              .build());

      menuDayRepository.saveAll(menu.getDaysList());
   }

   private Set<Recipe> getSet() {
      Set<Recipe> result = new HashSet<>();
      result.add(recipeMapper.recipeDtoMapToRecipe(
              recipeService.getRecipeDtoByUUID(UUID.fromString("a1e0db45-d91a-4367-a87b-e4d576563681"))));
      result.add(recipeMapper.recipeDtoMapToRecipe(
              recipeService.getRecipeDtoByUUID(UUID.fromString("1c972e1a-3653-45b9-b6c5-4012b465b47f"))));
      return result;
   }

   public void delete() {
      menuDayRepository.deleteById(1);
   }
}
