package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Product findByUuid(UUID id);

  Page<Product> findProductsByNameIsLike(String productName, Pageable pageable);

  Product findByName(String name);

  Product findById(int id);

  Page<Product> findProductsByNameIsLike(String productName, Pageable pageable);
}
