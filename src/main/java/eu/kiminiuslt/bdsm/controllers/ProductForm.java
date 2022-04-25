package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductForm {

  @GetMapping("/product")
  public String login(Model model) {
    model.addAttribute("product", new Product());
    return "productForm";
  }

  @PostMapping("/product")
  public String loggedOrNot(Product product) {

    return "productForm";
  }
}
