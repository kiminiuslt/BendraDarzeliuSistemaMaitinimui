package eu.kiminiuslt.bdsm.menu.model.dto;

import eu.kiminiuslt.bdsm.product.model.dto.ProductsNamesDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ProductShortageDto extends ProductsNamesDto {

  private Integer productId;
  private Double requiredGrams;
  private Double ownedKg;
  private Double shortageKg;
}
