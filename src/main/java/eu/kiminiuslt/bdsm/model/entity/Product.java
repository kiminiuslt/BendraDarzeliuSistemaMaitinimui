package eu.kiminiuslt.bdsm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "bdsm")
public class Product {
  @Id @GeneratedValue private Integer id;

  private UUID uuid;

  private String name;

  @Column(name = "water_g")
  private Double water;

  @Column(name = "dry_matirial_g")
  private Double dryMaterial;

  @Column(name = "proteins_g")
  private Double proteins;

  @Column(name = "vegetable_protein_g")
  private Double vegetableProtein;

  @Column(name = "carbohydrates_all_g")
  private Double carbohydrates;

  @Column(name = "mineral_substances_mg")
  private Double mineralSubstances;

  @Column(name = "sodium_mg")
  private Double sodium;

  @Column(name = "magnesium_mg")
  private Double magnesium;

  @Column(name = "phosphorus_mg")
  private Double phosphorus;

  @Column(name = "potassium_mg")
  private Double potassium;

  @Column(name = "calcium_mg")
  private Double calcium;

  @Column(name = "iodine_mkrg")
  private Double iodine;

  @Column(name = "vitamin_B2_mg")
  private Double vitaminB2;

  @Column(name = "niacin_vitamin_pp_mg")
  private Double niacinVitaminPP;

  @Column(name = "niacin_equivalent_mkrg")
  private Double niacinEquivalent;

  @Column(name = "vitamin_B6_mg")
  private Double vitaminB6;

  @Column(name = "alcohol_g")
  private Double alcohol;

  @Column(name = "energy_kJ")
  private Double energyKj;

  @Column(name = "energy_value_kcal")
  private Double energyValueKcal;

  @Column(name = "iron_mg")
  private Double iron;

  @Column(name = "vitamin_B1_mg")
  private Double vitaminB1;

  @Column(name = "zinc_mg")
  private Double zinc;

  @Column(name = "fat_g")
  private Double fat;

  @Column(name = "saturated_fatty_acids_g")
  private Double saturatedFattyAcids;

  @Column(name = "monounsaturated_fatty_acids_g")
  private Double monounsaturatedFattyAcids;

  @Column(name = "polyunsaturated_fatty_acids_g")
  private Double polyunsaturatedFattyAcids;

  @Column(name = "starch_g")
  private Double starch;

  @Column(name = "fiber_materials_g")
  private Double fiberMaterials;

  @Column(name = "selenium_mkrg")
  private Double selenium;

  @Column(name = "vitamin_a_retinol_mkrg")
  private Double vitaminARetinol;

  @Column(name = "vitamin_e_tocopherol_mg")
  private Double vitaminETocopherol;

  @Column(name = "folic_acid_mkrg")
  private Double folicAcid;

  @Column(name = "vitamin_C_mg")
  private Double vitaminC;

  @Column(name = "animal_protein_g")
  private Double animalProtein;

  @Column(name = "cholesterol_mg")
  private Double cholesterol;

  @Column(name = "vitamin_d_mkrg")
  private Double vitaminD;

  @Column(name = "vitamin_b12_mkrg")
  private Double vitaminB12;
}
