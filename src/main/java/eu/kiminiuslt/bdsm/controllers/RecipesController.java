package eu.kiminiuslt.bdsm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

  @GetMapping
  public String recipesAll() {
    return "recipie_all";
  }
}
