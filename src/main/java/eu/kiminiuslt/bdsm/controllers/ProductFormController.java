package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.model.ProductDto;
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
  public String openProductForm(Model model) {
    model.addAttribute("product", ProductDto.builder().build());
    return "productForm";
  }

  @PostMapping("/form")
  public String saveProduct(Model model, ProductDto product) {
    productService.addProduct(product);
    model.addAttribute("message", "Išsaugota");
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
  public String updateProduct(Model model, ProductDto product) {
    productService.updateProduct(product);
    model.addAttribute("productList", productService.getProducts());
    return "products";
  }

  @GetMapping("/delete")
  public String deleteProduct(Model model, @RequestParam UUID id) {
    productService.deleteProduct(id);
    model.addAttribute("productList", productService.getProducts());
    return "products";
  }
}
