package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.ProductDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public ProductDto mapToProductDto(Product product) {
    return ProductDto.builder()
        .kcal(product.getEnergyValueKcal())
        .carbs(product.getCarbohydrates())
        .fat(product.getFat())
        .protein(product.getProteins())
        .name(product.getName())
        .uuid(product.getUuid())
        .build();
  }
}
