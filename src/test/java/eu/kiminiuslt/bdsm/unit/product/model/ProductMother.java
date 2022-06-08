package eu.kiminiuslt.bdsm.unit.product.model;

import eu.kiminiuslt.bdsm.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.product.model.entity.Product;

import java.util.UUID;

public class ProductMother {
  private static final String NAME = "Yogurt";
  private static final Integer ID = 2;
  private static final UUID UU_ID = UUID.fromString("cc4038b9-d4a6-4830-8470-ac2bb5207a18");

  public static Product getProduct() {
    return Product.builder()
        .uuid(UU_ID)
        .name(NAME)
        .id(ID)
        .water(3.5)
        .dryMaterial(8.6)
        .proteins(5.5)
        .vegetableProtein(208.0)
        .carbohydrates(6.0)
        .mineralSubstances(0.8)
        .sodium(2.3)
        .magnesium(0.08)
        .phosphorus(5.9)
        .potassium(8.0)
        .calcium(9.0)
        .iodine(8.7)
        .vitaminB2(5.3)
        .niacinVitaminPP(0.07)
        .niacinEquivalent(3.0)
        .vitaminB6(3.9)
        .alcohol(1.1)
        .energyKj(0.1)
        .energyValueKcal(8.79)
        .iron(5.56)
        .vitaminB1(7.62)
        .zinc(308.0)
        .fat(4.0)
        .saturatedFattyAcids(8.92)
        .monounsaturatedFattyAcids(6.54)
        .polyunsaturatedFattyAcids(7.7)
        .starch(3.1)
        .fiberMaterials(0.97)
        .selenium(0.89)
        .vitaminARetinol(3.84)
        .vitaminETocopherol(3.33)
        .folicAcid(2.2)
        .vitaminC(9.1)
        .animalProtein(6.3)
        .cholesterol(4.7)
        .vitaminD(1.11)
        .vitaminB12(6.57)
        .sugar(1.0)
        .build();
  }

  public static ProductDto getProductDto() {
    Product product = getProduct();
    return ProductDto.builder()
        .uuid(product.getUuid())
        .name(product.getName())
        .water(product.getWater())
        .dryMaterial(product.getDryMaterial())
        .proteins(product.getProteins())
        .vegetableProtein(product.getVegetableProtein())
        .carbohydrates(product.getCarbohydrates())
        .mineralSubstances(product.getMineralSubstances())
        .sodium(product.getSodium())
        .magnesium(product.getMagnesium())
        .phosphorus(product.getPhosphorus())
        .potassium(product.getPotassium())
        .calcium(product.getCalcium())
        .iodine(product.getIodine())
        .vitaminB2(product.getVitaminB2())
        .niacinVitaminPP(product.getNiacinVitaminPP())
        .niacinEquivalent(product.getNiacinEquivalent())
        .vitaminB6(product.getVitaminB6())
        .alcohol(product.getAlcohol())
        .energyKj(product.getEnergyKj())
        .energyValueKcal(product.getEnergyValueKcal())
        .iron(product.getIron())
        .vitaminB1(product.getVitaminB1())
        .zinc(product.getZinc())
        .fat(product.getFat())
        .saturatedFattyAcids(product.getSaturatedFattyAcids())
        .monounsaturatedFattyAcids(product.getMonounsaturatedFattyAcids())
        .polyunsaturatedFattyAcids(product.getPolyunsaturatedFattyAcids())
        .starch(product.getStarch())
        .fiberMaterials(product.getFiberMaterials())
        .selenium(product.getSelenium())
        .vitaminARetinol(product.getVitaminARetinol())
        .vitaminETocopherol(product.getVitaminETocopherol())
        .folicAcid(product.getFolicAcid())
        .vitaminC(product.getVitaminC())
        .animalProtein(product.getAnimalProtein())
        .cholesterol(product.getCholesterol())
        .vitaminD(product.getVitaminD())
        .vitaminB12(product.getVitaminB12())
        .sugar(product.getSugar())
        .build();
  }

  public static ProductForRecipeDto getProductForRecipeDto() {
    Product product = getProduct();
    return ProductForRecipeDto.builder()
        .uuid(product.getUuid())
        .name(product.getName())
        .proteins(product.getProteins())
        .fat(product.getFat())
        .carbohydrates(product.getCarbohydrates())
        .energyValueKcal(product.getEnergyValueKcal())
        .build();
  }

  public static ProductsNamesDto getProductsNamesDto() {
    return ProductsNamesDto.builder().uuid(UU_ID).name(NAME).build();
  }
}
