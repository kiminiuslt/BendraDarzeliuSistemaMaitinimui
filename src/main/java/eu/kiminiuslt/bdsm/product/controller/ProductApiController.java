package eu.kiminiuslt.bdsm.product.controller;

import eu.kiminiuslt.bdsm.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductApiController implements ProductApiDocumentation {

  private final ProductService productService;

  @Override
  public Page<ProductDto> getProductsPaginated(int page, int size) {
    return productService.getPageableProduct(PageRequest.of(page, size));
  }

  @Override
  public ResponseEntity<Void> createProduct(ProductDto productDto) {
    productService.addProduct(productDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateProduct(ProductDto productDto) {
    productService.updateProduct(productDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> deleteProduct(UUID uuid) {
    productService.deleteProduct(uuid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
