package eu.kiminiuslt.bdsm.core.product.mapper;

import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

  /***
   * Mapping Product entity to ProductDto
   * @param product not null
   * @return ProductDto
   * @since 1.0.0
   */
  public ProductDto mapToProductDto(Product product) {
    return ProductDto.builder()
        .uuid(product.getUuid())
        .name(product.getName())
        .proteins(product.getProteins())
        .fat(product.getFat())
        .carbohydrates(product.getCarbohydrates())
        .energyValueKcal(product.getEnergyValueKcal())
        .water(product.getWater())
        .dryMaterial(product.getDryMaterial())
        .vegetableProtein(product.getVegetableProtein())
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
        .iron(product.getIron())
        .vitaminB1(product.getVitaminB1())
        .zinc(product.getZinc())
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

  /***
   * Mapping ProductDto to Product entity
   * @param productDto not null
   * @return Product
   * @since 1.0.0
   */
  public Product mapToProduct(ProductDto productDto) {
    return Product.builder()
        .uuid(UUID.randomUUID())
        .name(productDto.getName())
        .proteins(productDto.getProteins())
        .fat(productDto.getFat())
        .carbohydrates(productDto.getCarbohydrates())
        .energyValueKcal(productDto.getEnergyValueKcal())
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
        .monounsaturatedFattyAcids(productDto.getMonounsaturatedFattyAcids())
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
        .sugar(productDto.getSugar())
        .build();
  }

  /***
   * Mapping Product entity to ProductsNamesDto
   * @param product not null
   * @return ProductsNamesDto
   * @since 1.0.0
   */
  public ProductsNamesDto productMapToProductNamesDto(Product product) {
    return ProductsNamesDto.builder().uuid(product.getUuid()).name(product.getName()).build();
  }

  /***
   * Mapping ProductDto to Product entity with entity ID reusing mapToProduct() method
   * @param productDto not null
   * @param product must contain id
   * @return Product
   * @since 1.0.0
   */
  public Product mapToProductForUpdate(ProductDto productDto, Product product) {
    Product result = mapToProduct(productDto);
    result.setId(product.getId());
    return result;
  }
}
