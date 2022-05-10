package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-finder")
public class SearchController {
  private final ProductService productService;

  private String getSearch(Model model, @RequestParam String findProductName, Pageable pageable) {

    model.addAttribute(
        "recipeDto", productService.getProductByNamePageable(findProductName, pageable));
    return "/recipe/recipe-form";
  }
}
