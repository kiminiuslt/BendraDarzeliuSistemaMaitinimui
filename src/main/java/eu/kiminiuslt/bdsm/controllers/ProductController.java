package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.helpers.MessageService;
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
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  private final MessageService messageService;

  @GetMapping
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

  @GetMapping("/productForm")
  public String openProductForm(Model model, String message) {
    model.addAttribute("ProductDto", ProductDto.builder().build());
    model.addAttribute("message", messageService.getMessage(message));
    return "product-form";
  }

  @PostMapping("/productForm")
  public String saveProduct(ProductDto product) {
    productService.addProduct(product);
    return "redirect:/products/productForm?message=create.product.successes";
  }

  @GetMapping("/{uuid}/update")
  public String getUpdateProduct(Model model, @PathVariable("uuid") UUID id) {
    model.addAttribute("ProductDto", productService.getProductByUUID(id));
    return "product-form";
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
