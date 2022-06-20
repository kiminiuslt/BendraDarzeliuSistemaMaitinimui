package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Service
public class MenuCalculationsService {

  public Double getDayEnergyValue(Set<RecipeDto> dayRecipesDto) {
    return dayRecipesDto.stream()
        .mapToDouble(RecipeDto::getAllEnergyValueKcal)
        .map(this::round)
        .sum();
  }

  public Double getDayEnergyValueLittleOnes(Set<RecipeDto> dayRecipesDto) {
    return dayRecipesDto.stream()
        .mapToDouble(RecipeDto::getAllEnergyValueKcalLittleOnes)
        .map(this::round)
        .sum();
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
