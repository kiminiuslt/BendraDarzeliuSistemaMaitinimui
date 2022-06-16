package eu.kiminiuslt.bdsm.jpa.repository;

import eu.kiminiuslt.bdsm.jpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  Product findByUuid(UUID id);

  Page<Product> findProductsByNameIsLike(String productName, Pageable pageable);

  Product findByName(String name);

  Product findById(int id);
}
