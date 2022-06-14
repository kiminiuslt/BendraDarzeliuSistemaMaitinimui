package eu.kiminiuslt.bdsm.product.controller;

import eu.kiminiuslt.bdsm.helpers.MessageService;
import eu.kiminiuslt.bdsm.product.model.dto.ProductApiResponseDto;
import eu.kiminiuslt.bdsm.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController implements ProductApiDocumentation {

  private final ProductService productService;
  private final MessageService messageService;

  public ProductApiResponseDto getProducts() {
    return ProductApiResponseDto.builder().products(productService.getProductsList()).build();
  }
}
