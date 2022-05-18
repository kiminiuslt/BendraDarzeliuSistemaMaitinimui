package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {
  public ProductDto mapToProductDto(Product product) {
    return ProductDto.builder()
        .energyValueKcal(product.getEnergyValueKcal())
        .carbohydrates(product.getCarbohydrates())
        .fat(product.getFat())
        .proteins(product.getProteins())
        .name(product.getName())
        .uuid(product.getUuid())
        .build();
  }

  public Product mapToProduct(ProductDto productDto) {
    return Product.builder()
        .uuid(UUID.randomUUID())
        .name(productDto.getName())
        .water(productDto.getWater())
        .dryMaterial(productDto.getDryMaterial())
        .proteins(productDto.getProteins())
        .vegetableProtein(productDto.getVegetableProtein())
        .carbohydrates(productDto.getCarbohydrates())
        .mineralSubstances(productDto.getMineralSubstances())
        .sodium(productDto.getSodium())
        .magnesium(productDto.getMagnesium())
        .phosphorus(productDto.getPhosphorus())
        .potassium(productDto.getPotassium())
        .calcium(productDto.getCalcium())
        .iodine(productDto.getIodine())
        .vitaminB2(productDto.getVitaminB2())
        .niacinVitaminPP(productDto.getNiacinVitaminPP())
        .niacinEquivalent(productDto.getNiacinEquivalent())
        .vitaminB6(productDto.getVitaminB6())
        .alcohol(productDto.getAlcohol())
        .energyKj(productDto.getEnergyKj())
        .energyValueKcal(productDto.getEnergyValueKcal())
        .iron(productDto.getIron())
        .vitaminB1(productDto.getVitaminB1())
        .zinc(productDto.getZinc())
        .fat(productDto.getFat())
        .saturatedFattyAcids(productDto.getSaturatedFattyAcids())
        .monounsaturatedFattyAcids(productDto.getSaturatedFattyAcids())
        .polyunsaturatedFattyAcids(productDto.getPolyunsaturatedFattyAcids())
        .starch(productDto.getStarch())
        .fiberMaterials(productDto.getFiberMaterials())
        .selenium(productDto.getSelenium())
        .vitaminARetinol(productDto.getVitaminARetinol())
        .vitaminETocopherol(productDto.getVitaminETocopherol())
        .folicAcid(productDto.getFolicAcid())
        .vitaminC(productDto.getVitaminC())
        .animalProtein(productDto.getAnimalProtein())
        .cholesterol(productDto.getCholesterol())
        .vitaminD(productDto.getVitaminD())
        .vitaminB12(productDto.getVitaminB12())
        .energyValueKcal(productDto.getEnergyValueKcal())
        .sugar(productDto.getSugar())
        .build();
  }

  public ProductForRecipeDto productMapToProductForRecipeDto(Product product) {
    return ProductForRecipeDto.builder()
        .uuid(product.getUuid())
        .name(product.getName())
        .proteins(product.getProteins())
        .fat(product.getFat())
        .carbohydrates(product.getCarbohydrates())
        .energyValueKcal(product.getEnergyValueKcal())
        .build();
  }

  public ProductsNamesDto productMapToProductNamesDto(Product product) {
    return ProductsNamesDto.builder().uuid(product.getUuid()).name(product.getName()).build();
  }
}
