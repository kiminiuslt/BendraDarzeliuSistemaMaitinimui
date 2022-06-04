package eu.kiminiuslt.bdsm.recipe.model.dto;

import eu.kiminiuslt.bdsm.product.model.entity.Product;
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
  private Double quantity;
}
