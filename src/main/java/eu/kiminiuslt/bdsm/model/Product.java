package eu.kiminiuslt.bdsm.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  private final int AMOUNT_OF_MATERIAL_GRAMS = 100;
  private UUID uuid;
  private String name;
  private double kcal;
  private double carbs;
}
