package eu.kiminiuslt.bdsm.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
  private final int AMOUNT_OF_MATERIAL_GRAMS = 100;
  private UUID uuid;
  private String name;
  private double water;
  private double dryMaterial;
  private double proteins;
  private double vegetableProtein;
  private double carbohydrates;
  private double mineralSubstances;
  private double sodium;
  private double magnesium;
  private double phosphorus;
  private double potassium;
  private double calcium;
  private double iodine;
  private double vitaminB2;
  private double niacinVitaminPP;
  private double niacinEquivalent;
  private double vitaminB6;
  private double alcohol;
  private double energyKj;
  private double energyValueKcal;
  private double iron;
  private double vitaminB1;
  private double zinc;
  private double fat;
  private double saturatedFattyAcids;
  private double monounsaturatedFattyAcids;
  private double polyunsaturatedFattyAcids;
  private double starch;
  private double fiberMaterials;
  private double selenium;
  private double vitaminARetinol;
  private double vitaminETocopherol;
  private double folicAcid;
  private double vitaminC;
  private double animalProtein;
  private double cholesterol;
  private double vitaminD;
  private double vitaminB12;
  private double sugar;
}
