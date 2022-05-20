package eu.kiminiuslt.bdsm.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecipeDto {
  private Integer id;
  private UUID uuid;
  private String recipeName;
  private String recipeText;
  private Set<ProductAndQuantityDto> productsList;

  public Double allProteins() {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getProteins() / 100) * e.getQuantity())
            .sum());
  }

  public Double allFat() {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getFat() / 100) * e.getQuantity())
            .sum());
  }

  public Double allCarbohydrates() {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getCarbohydrates() / 100) * e.getQuantity())
            .sum());
  }

  public Double allEnergyValueKcal() {
    return round(
        productsList.stream()
            .mapToDouble(e -> (e.getProduct().getEnergyValueKcal() / 100) * e.getQuantity())
            .sum());
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
