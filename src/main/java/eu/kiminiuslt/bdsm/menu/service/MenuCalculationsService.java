package eu.kiminiuslt.bdsm.menu.service;

import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Service
public class MenuCalculationsService {

  public Double getDayEnergyValue(Set<RecipeDto> dayRecipesDto) {
    return dayRecipesDto.stream().mapToDouble(this::allEnergyValueKcal).sum();
  }

// Should be in MenuCalculationsService class
  public Double allEnergyValueKcal(RecipeDto recipeDto) {
    return round(
        recipeDto.getProductsList().stream()
            .mapToDouble(obj -> (obj.getProduct().getEnergyValueKcal() / 100) * obj.getQuantity())
            .sum());
  }
  // Should be in MenuCalculationsService class
  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
