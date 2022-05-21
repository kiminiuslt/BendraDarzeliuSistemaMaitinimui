package eu.kiminiuslt.bdsm.menu.repository;

import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Product, Integer> {}
