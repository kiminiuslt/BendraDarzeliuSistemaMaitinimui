package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.ProductShortageDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ProductShortageHelper {

  public ProductShortageDto setShortage(
      double warehouseAmountKg, ProductShortageDto productShortageDto) {
    productShortageDto.setOwnedKg(
        round(warehouseAmountKg - productShortageDto.getOtherDaysRequiredGrams() / 1000));

    productShortageDto.setShortageKg(
        round(productShortageDto.getRequiredGrams() / 1000 - productShortageDto.getOwnedKg()));
    return productShortageDto;
  }

  public ProductShortageDto setShortageMax(ProductShortageDto productShortageDto, double allGrams) {
    productShortageDto.setShortageKg(countShortage(allGrams));
    productShortageDto.setOwnedKg(0.0);
    return productShortageDto;
  }

  private Double countShortage(Double requiredGrams) {
    return round(requiredGrams / 1000);
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public boolean containsInList(
      List<ProductShortageDto> productShortageDto, ProductAndQuantityDto productAndQuantityDto) {
    return productShortageDto.stream()
        .anyMatch(o -> productAndQuantityDto.getProductUUID().equals(o.getUuid()));
  }
}
