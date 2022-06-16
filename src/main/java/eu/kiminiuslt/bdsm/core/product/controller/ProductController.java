package eu.kiminiuslt.bdsm.core.product.controller;

import eu.kiminiuslt.bdsm.core.product.service.ProductService;
import eu.kiminiuslt.bdsm.core.helpers.MessageService;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIETIST')")
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
          Pageable pageable,
      String message) {
    model.addAttribute("productListPages", productService.getPageableProduct(pageable));
    model.addAttribute("message", messageService.getMessage(message));
    return "/products/products";
  }

  @GetMapping("/productForm")
  public String openProductForm(Model model) {
    model.addAttribute("productDto", productService.getEmptyProductDto());
    return "products/product-form";
  }

  @PostMapping("/productForm")
  public String saveProduct(@Valid ProductDto productDto, BindingResult errors) {
    if (errors.hasErrors()) {
      return "products/product-form";
    }
    productService.addProduct(productDto);
    return "redirect:/products?message=product.create.successes";
  }

  @GetMapping("/{uuid}/update")
  public String getUpdateProduct(Model model, @PathVariable("uuid") UUID id) {
    model.addAttribute("productDto", productService.getProductDtoByUUID(id));
    return "products/product-form";
  }

  @PostMapping("/{uuid}/update")
  public String updateProduct(ProductDto product) {
    productService.updateProduct(product);
    return "redirect:/products?message=product.update.successes";
  }

  @GetMapping("/{uuid}/delete")
  public String deleteProduct(@PathVariable("uuid") UUID id) {
    productService.deleteProduct(id);
    return "redirect:/products?message=product.delete.successes";
  }
}
