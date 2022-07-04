package eu.kiminiuslt.bdsm.unit.recipe;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.jpa.entity.ProductAndQuantity;
import eu.kiminiuslt.bdsm.unit.product.ProductMother;

import java.util.HashSet;
import java.util.Set;

public class ProductAndQuantityMother {

  public static Set<ProductAndQuantityDto> getDtoList() {
    Set<ProductAndQuantityDto> result = new HashSet<>();

    for (int i = 0; i < 10; i++) {
      result.add(getDto(i));
    }
    return result;
  }

  public static Set<ProductAndQuantity> getEntityList() {
    Set<ProductAndQuantity> result = new HashSet<>();

    for (int i = 0; i < 10; i++) {
      result.add(getEntity(i));
    }
    return result;
  }

  private static ProductAndQuantity getEntity(int i) {
    return ProductAndQuantity.builder()
        .id(i)
        .productId(ProductMother.getProduct().getId())
        .quantityNet((double) i)
        .quantityGross((double) i + 0.3)
        .quantityNetLittleOnes((double) i - 0.05)
        .quantityGrossLittleOnes((double) i - 0.08)
        .build();
  }

  private static ProductAndQuantityDto getDto(int i) {
    return ProductAndQuantityDto.builder()
        .id(i)
        .productUUID(ProductMother.getProduct().getUuid())
        .quantityNet((double) i)
        .quantityGross((double) i + 0.3)
        .quantityNetLittleOnes((double) i - 0.05)
        .quantityGrossLittleOnes((double) i - 0.08)
        .product(ProductMother.getProduct())
        .build();
  }
}
