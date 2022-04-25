package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.model.Product;
import eu.kiminiuslt.bdsm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductForm {
  private final ProductService productService;

  @GetMapping("/product")
  public String login(Model model) {
    model.addAttribute("product", new Product());
    return "productForm";
  }

  @PostMapping("/product")
  public String loggedOrNot(Product product) {
    productService.addProduct(product);
    return "productForm";
  }
}
