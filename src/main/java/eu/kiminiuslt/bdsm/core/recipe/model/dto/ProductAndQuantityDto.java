package eu.kiminiuslt.bdsm.core.recipe.model.dto;

import eu.kiminiuslt.bdsm.jpa.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ProductAndQuantityDto {
  private Integer id;
  private UUID productUUID;
  private Product product;
  private Double quantityGross;
  private Double quantityNet;
  private Double quantityGrossLittleOnes;
  private Double quantityNetLittleOnes;
}
