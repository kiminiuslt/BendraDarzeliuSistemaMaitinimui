package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.model.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public void addProduct(Product product) {
    productRepository.save(product);
  }

  public List<Product> getProducts() {
    return productRepository.getList();
  }

  public Product getProductByUUID(UUID id) {
    return productRepository.getProductByUUID(id);
  }

  public void updateProduct(Product product) {
    productRepository.update(product);
  }
}
