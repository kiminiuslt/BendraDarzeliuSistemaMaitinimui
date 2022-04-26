package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

  private Map<UUID, Product> products = new HashMap<>();

  public void save(Product product) {
    product.setUuid(UUID.randomUUID());
    products.put(product.getUuid(), product);
  }

  public List<Product> getList() {
    return new ArrayList<>(products.values());
  }

  public Product getProductByUUID(UUID id) {
    return products.get(id);
  }

  public void update(Product product) {
    products.put(product.getUuid(), product);
  }
}
