package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.product.service.ProductService;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.api.documentation.CrudApiDocumentation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@PreAuthorize("hasRole('DIETIST')")
@Api(tags = "Product Controller")
public class ProductApiController implements CrudApiDocumentation<ProductDto> {

  private final ProductService productService;

  @Override
  @ApiOperation(value = "Create product", httpMethod = "POST")
  public ResponseEntity<Void> create(ProductDto object) {
    productService.addProduct(object);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @ApiOperation(
      value = "Get products page",
      notes = "Chunk of all products list implemented by pagination",
      httpMethod = "GET")
  public Page<ProductDto> readPaginated(int page, int size) {
    return productService.getPageableProduct(PageRequest.of(page, size));
  }

  @Override
  @ApiOperation(value = "Update product", httpMethod = "PUT", notes = "Updates only one product")
  public ResponseEntity<Void> update(ProductDto object) {
    productService.updateProduct(object);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  @ApiOperation(value = "Delete product", httpMethod = "DELETE")
  public ResponseEntity<Void> delete(UUID uuid) {
    productService.deleteProduct(uuid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
