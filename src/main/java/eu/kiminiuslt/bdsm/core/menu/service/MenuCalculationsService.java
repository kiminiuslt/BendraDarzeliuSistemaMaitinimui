package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeNamesDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Service
public class MenuCalculationsService {

  public Double getDayEnergyValue(Set<RecipeNamesDto> dayRecipesDto) {
    return dayRecipesDto.stream()
        .mapToDouble(RecipeNamesDto::getAllEnergyValueKcal)
        .map(this::round)
        .sum();
  }

  public Double getDayEnergyValueLittleOnes(Set<RecipeNamesDto> dayRecipesDto) {
    return dayRecipesDto.stream()
        .mapToDouble(RecipeNamesDto::getAllEnergyValueKcalLittleOnes)
        .map(this::round)
        .sum();
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
