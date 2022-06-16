package eu.kiminiuslt.bdsm.core.product.model.dto;

import eu.kiminiuslt.bdsm.jpa.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductApiResponseDto {

  @ApiModelProperty(notes = "Products list", required = true)
  private List<Product> products;
}
