package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public void save(Product product){
        products.add(product);
    }
}
