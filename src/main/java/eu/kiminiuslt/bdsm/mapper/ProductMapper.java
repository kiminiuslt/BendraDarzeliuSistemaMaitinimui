package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public ProductDto mapToProductDto(Product product) {
    return ProductDto.builder()
        .energyValueKcal(product.getEnergyValueKcal())
        .carbohydrates(product.getCarbohydrates())
        .fat(product.getFat())
        .proteins(product.getProteins())
        .name(product.getName())
        .uuid(product.getUuid())
        .build();
  }

  public ProductForRecipeDto productMapToProductForRecipeDto(Product product) {
    return ProductForRecipeDto.builder()
        .id(product.getId())
        .name(product.getName())
        .proteins(product.getProteins())
        .fat(product.getFat())
        .carbohydrates(product.getCarbohydrates())
        .energyValueKcal(product.getEnergyValueKcal())
        .build();
  }
}
