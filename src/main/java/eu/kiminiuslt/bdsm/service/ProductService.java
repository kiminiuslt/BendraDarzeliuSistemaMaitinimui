package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.model.ProductDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public void addProduct(ProductDto product) {

    productRepository.save(
        Product.builder()
            .uuid(UUID.randomUUID())
            .name(product.getName())
            .carbs(product.getCarbs())
            .kcal(product.getKcal())
            .build());
  }

  public List<ProductDto> getProducts() {
    return productRepository.findAll().stream()
        .map((obj) -> productMapper.mapTo(obj))
        .collect(Collectors.toList());
  }

  public ProductDto getProductByUUID(UUID id) {
    return productMapper.mapTo(productRepository.findByUuid(id));
  }

  @Transactional
  public void updateProduct(ProductDto productDto) {
    Product product =
        productRepository.findByUuid(productDto.getUuid()).toBuilder()
            .name(productDto.getName())
            .carbs(productDto.getCarbs())
            .kcal(productDto.getKcal())
            .build();

    productRepository.save(product);
  }

  @Transactional
  public void deleteProduct(UUID id) {
    productRepository.deleteById(productRepository.findByUuid(id).getId());
  }
}
