package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.model.ProductDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    //    return productMapper.mapTo(productRepository.findById(id));
    return null;
  }

  public void updateProduct(ProductDto product) {
    //    productRepository.update(product);
  }

  public void deleteProduct(UUID id) {
    //    productRepository.delete(id);
  }
}
