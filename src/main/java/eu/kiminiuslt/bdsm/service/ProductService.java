package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.model.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public void addProduct(Product product) {
    productRepository.save(product);
  }
}
