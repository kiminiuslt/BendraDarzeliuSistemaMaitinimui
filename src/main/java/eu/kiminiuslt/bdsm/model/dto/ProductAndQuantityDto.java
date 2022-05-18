package eu.kiminiuslt.bdsm.model.dto;

import eu.kiminiuslt.bdsm.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class ProductAndQuantityDto {
  private int id;
  private Product product;
  private double quantity;
}
