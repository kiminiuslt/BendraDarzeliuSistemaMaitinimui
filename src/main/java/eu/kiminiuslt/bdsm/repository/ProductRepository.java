package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Product findByUuid(UUID id);

  Product findByName(String name);

  Product findById(int id);
}
