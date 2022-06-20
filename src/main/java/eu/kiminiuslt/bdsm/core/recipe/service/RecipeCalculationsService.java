package eu.kiminiuslt.bdsm.core.recipe.service;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Service
public class RecipeCalculationsService {

  public RecipeDto sumOfMainMaterials(RecipeDto recipeDto) {

    recipeDto.setAllEnergyValueKcal(allEnergyValueKcal(recipeDto.getProductsList()));
    recipeDto.setAllCarbohydrates(allCarbohydrates(recipeDto.getProductsList()));
    recipeDto.setAllFat(allFat(recipeDto.getProductsList()));
    recipeDto.setAllProteins(allProteins(recipeDto.getProductsList()));

    recipeDto.setAllEnergyValueKcalLittleOnes(
        allEnergyValueKcalLittleOnes(recipeDto.getProductsList()));
    recipeDto.setAllCarbohydratesLittleOnes(
        allCarbohydratesLittleOnes(recipeDto.getProductsList()));
    recipeDto.setAllFatLittleOnes(allFatLittleOnes(recipeDto.getProductsList()));
    recipeDto.setAllProteinsLittleOnes(allProteinsLittleOnes(recipeDto.getProductsList()));
    return recipeDto;
  }

  private Double allProteinsLittleOnes(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getProteins() / 100) * e.getQuantityNetLittleOnes())
            .sum());
  }

  private Double allFatLittleOnes(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getFat() / 100) * e.getQuantityNetLittleOnes())
            .sum());
  }

  private Double allCarbohydratesLittleOnes(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(
                e -> (e.getProduct().getCarbohydrates() / 100) * e.getQuantityNetLittleOnes())
            .sum());
  }

  private Double allEnergyValueKcalLittleOnes(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(
                e -> (e.getProduct().getEnergyValueKcal() / 100) * e.getQuantityNetLittleOnes())
            .sum());
  }

  private Double allProteins(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getProteins() / 100) * e.getQuantityNet())
            .sum());
  }

  private Double allFat(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getFat() / 100) * e.getQuantityNet())
            .sum());
  }

  private Double allCarbohydrates(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getCarbohydrates() / 100) * e.getQuantityNet())
            .sum());
  }

  private Double allEnergyValueKcal(Set<ProductAndQuantityDto> productsList) {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getEnergyValueKcal() / 100) * e.getQuantityNet())
            .sum());
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
