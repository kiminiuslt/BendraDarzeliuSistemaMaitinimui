package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//public Product findById();
}
