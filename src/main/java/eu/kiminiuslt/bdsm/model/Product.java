package eu.kiminiuslt.bdsm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private final int AMOUNT_OF_MATERIAL_GRAMS = 100;
  private String name;
  private double kcal;
  private double carbs;
  //  private Map<String, Double> nutritionalAndEnergy = new HashMap<>();
}
