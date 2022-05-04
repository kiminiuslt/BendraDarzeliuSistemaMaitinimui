package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.model.ProductDto;
import eu.kiminiuslt.bdsm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductFormController {

  private final ProductService productService;

  @GetMapping("/form")
  public String openProductForm(Model model) {
    model.addAttribute("ProductDto", ProductDto.builder().build());
    return "productForm";
  }

  @GetMapping("/home")
  public String homePage() {
    return "home";
  }

  @PostMapping("/form")
  public String saveProduct(Model model, ProductDto product) {
    productService.addProduct(product);
    model.addAttribute("ProductDto", ProductDto.builder().build());
    model.addAttribute("message", "Išsaugota");
    return "productForm";
  }

  @GetMapping("/list")
  public String getProducts(
      Model model,
      @PageableDefault(
              size = 30,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    model.addAttribute("productListPages", productService.getPageableProduct(pageable));
    return "products";
  }

  @GetMapping("/{uuid}/update")
  public String getUpdateProduct(Model model, @PathVariable("uuid") UUID id) {
    model.addAttribute("ProductDto", productService.getProductByUUID(id));
    return "productForm";
  }

  @PostMapping("/{uuid}/update")
  public String updateProduct(
      Model model,
      ProductDto product,
      @PageableDefault(
              size = 30,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    productService.updateProduct(product);
    model.addAttribute("productListPages", productService.getPageableProduct(pageable));
    return "products";
  }

  @GetMapping("/{uuid}/delete")
  public String deleteProduct(
      Model model,
      @PathVariable("uuid") UUID id,
      @PageableDefault(
              size = 30,
              sort = {"name"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    productService.deleteProduct(id);
    model.addAttribute("productListPages", productService.getPageableProduct(pageable));
    return "products";
  }
}
