package eu.kiminiuslt.bdsm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food-menu")
public class FoodMenuController {

  @GetMapping
  public String foodMenu() {
    return "/foodMenu/food-menu";
  }
}
