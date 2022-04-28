package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.ProductDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public ProductDto mapTo(Product product) {
    return ProductDto.builder()
        .kcal(product.getKcal())
        .carbs(product.getCarbs())
        .name(product.getName())
        .uuid(product.getUuid())
        .build();
  }
}
