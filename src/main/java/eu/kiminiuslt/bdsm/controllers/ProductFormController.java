package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.model.Product;
import eu.kiminiuslt.bdsm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductFormController {

  private final ProductService productService;

  @GetMapping("/form")
  public String login(Model model) {
    model.addAttribute("product", Product.builder().build());
    return "productForm";
  }

  @PostMapping("/form")
  public String loggedOrNot(Product product) {
    productService.addProduct(product);

    return "productForm";
  }

  @GetMapping("/list")
  public String getProducts(Model model) {
    model.addAttribute("productList", productService.getProducts());
    return "products";
  }

  @GetMapping("/update")
  public String getUpdateProduct(Model model, @RequestParam UUID id) {
    model.addAttribute("product", productService.getProductByUUID(id));
    return "productForm";
  }

  @PostMapping("/update")
  public String getUpdateProduct(Model model, Product product) {
    productService.updateProduct(product);
    model.addAttribute("productList", productService.getProducts());
    return "products";
  }

  @PostMapping("/delete")
  public String deleteProduct(Model model, @RequestParam UUID id) {
    productService.deleteProduct(id);
    model.addAttribute("productList", productService.getProducts());
    return "products";
  }
}
