package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.ProductAndQuantityDto;
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

  public Set<ProductAndQuantity> dtoToEntity(Set<ProductAndQuantityDto> paqDto) {
    Set<ProductAndQuantity> result = new HashSet<>();
    paqDto.forEach(e -> result.add(entity(e)));
    return result;
  }

  private ProductAndQuantity entity(ProductAndQuantityDto e) {
    return ProductAndQuantity.builder()
        .quantity(e.getQuantity())
        .productId(productService.getProductByUUID(e.getProduct().getUuid()).getId())
        .build();
  }
}
