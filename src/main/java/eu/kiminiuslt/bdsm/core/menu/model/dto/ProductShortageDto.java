package eu.kiminiuslt.bdsm.core.menu.model.dto;

import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ProductShortageDto extends ProductsNamesDto {

  private Integer productId;
  private Double requiredGrams;
  private Double otherDaysRequiredGrams;
  private Double ownedKg;
  private Double shortageKg;
}
