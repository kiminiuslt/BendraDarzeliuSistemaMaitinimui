package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import eu.kiminiuslt.bdsm.model.entity.ProductAndQuantity;
import eu.kiminiuslt.bdsm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductAndQuantityMapper {

  private final ProductService productService;

  public Set<ProductAndQuantity> dtoToEntity(Set<ProductAndQuantityDto> productsList) {
    Set<ProductAndQuantity> result = new HashSet<>();
    productsList.forEach(e -> result.add(getEntity(e)));
    return result;
  }

  public Set<ProductAndQuantityDto> entityToDto(Set<ProductAndQuantity> productsList) {
    Set<ProductAndQuantityDto> result = new HashSet<>();
    productsList.forEach(e -> result.add(getDto(e)));
    return result;
  }

  private ProductAndQuantity getEntity(ProductAndQuantityDto e) {
    if (e.getId() == null) {
      return ProductAndQuantity.builder()
          .quantity(e.getQuantity())
          .productId(productService.getProductByUUID(e.getProduct().getUuid()).getId())
          .build();
    }
    return ProductAndQuantity.builder()
        .id(e.getId())
        .quantity(e.getQuantity())
        .productId(productService.getProductByUUID(e.getProduct().getUuid()).getId())
        .build();
  }

  private ProductAndQuantityDto getDto(ProductAndQuantity e) {
    Product product = productService.getProductById(e.getProductId());
    return ProductAndQuantityDto.builder()
        .id(e.getId())
        .productUUID(product.getUuid())
        .quantity(e.getQuantity())
        .product(product)
        .build();
  }
}
