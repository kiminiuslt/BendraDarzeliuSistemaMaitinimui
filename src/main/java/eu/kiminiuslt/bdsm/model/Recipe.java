package eu.kiminiuslt.bdsm.model;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
  private String name;
  private String recipeText;
  private Map<Product, Double> ingredient = new HashMap<>();
}
