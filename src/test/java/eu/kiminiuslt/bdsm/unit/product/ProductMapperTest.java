package eu.kiminiuslt.bdsm.unit.product;

import eu.kiminiuslt.bdsm.core.product.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  @InjectMocks private ProductMapper productMapper;

  @Test
  void mapToProductDto() {
    Product given = ProductMother.getProduct();
    ProductDto expected = ProductMother.getProductDto();
    ProductDto result = productMapper.mapToProductDto(given);

    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getUuid(), result.getUuid());
    assertEquals(expected.getProteins(), result.getProteins());
    assertEquals(expected.getFat(), result.getFat());
    assertEquals(expected.getCarbohydrates(), result.getCarbohydrates());
    assertEquals(expected.getEnergyValueKcal(), result.getEnergyValueKcal());
    assertEquals(expected.getWater(), result.getWater());
    assertEquals(expected.getDryMaterial(), result.getDryMaterial());
    assertEquals(expected.getVegetableProtein(), result.getVegetableProtein());
    assertEquals(expected.getMineralSubstances(), result.getMineralSubstances());
    assertEquals(expected.getSodium(), result.getSodium());
    assertEquals(expected.getMagnesium(), result.getMagnesium());
    assertEquals(expected.getPhosphorus(), result.getPhosphorus());
    assertEquals(expected.getPotassium(), result.getPotassium());
    assertEquals(expected.getCalcium(), result.getCalcium());
    assertEquals(expected.getIodine(), result.getIodine());
    assertEquals(expected.getVitaminB2(), result.getVitaminB2());
    assertEquals(expected.getNiacinVitaminPP(), result.getNiacinVitaminPP());
    assertEquals(expected.getNiacinEquivalent(), result.getNiacinEquivalent());
    assertEquals(expected.getVitaminB6(), result.getVitaminB6());
    assertEquals(expected.getAlcohol(), result.getAlcohol());
    assertEquals(expected.getEnergyKj(), result.getEnergyKj());
    assertEquals(expected.getIron(), result.getIron());
    assertEquals(expected.getVitaminB1(), result.getVitaminB1());
    assertEquals(expected.getZinc(), result.getZinc());
    assertEquals(expected.getSaturatedFattyAcids(), result.getSaturatedFattyAcids());
    assertEquals(expected.getMonounsaturatedFattyAcids(), result.getMonounsaturatedFattyAcids());
    assertEquals(expected.getPolyunsaturatedFattyAcids(), result.getPolyunsaturatedFattyAcids());
    assertEquals(expected.getStarch(), result.getStarch());
    assertEquals(expected.getFiberMaterials(), result.getFiberMaterials());
    assertEquals(expected.getSelenium(), result.getSelenium());
    assertEquals(expected.getVitaminARetinol(), result.getVitaminARetinol());
    assertEquals(expected.getVitaminETocopherol(), result.getVitaminETocopherol());
    assertEquals(expected.getFolicAcid(), result.getFolicAcid());
    assertEquals(expected.getVitaminC(), result.getVitaminC());
    assertEquals(expected.getAnimalProtein(), result.getAnimalProtein());
    assertEquals(expected.getCholesterol(), result.getCholesterol());
    assertEquals(expected.getVitaminD(), result.getVitaminD());
    assertEquals(expected.getVitaminB12(), result.getVitaminB12());
    assertEquals(expected.getSugar(), result.getSugar());
  }

  @Test
  void mapToProduct() {
    ProductDto given = ProductMother.getProductDto();
    Product expected = ProductMother.getProduct();
    Product result = productMapper.mapToProduct(given);

    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getProteins(), result.getProteins());
    assertEquals(expected.getFat(), result.getFat());
    assertEquals(expected.getCarbohydrates(), result.getCarbohydrates());
    assertEquals(expected.getEnergyValueKcal(), result.getEnergyValueKcal());
    assertEquals(expected.getWater(), result.getWater());
    assertEquals(expected.getDryMaterial(), result.getDryMaterial());
    assertEquals(expected.getVegetableProtein(), result.getVegetableProtein());
    assertEquals(expected.getMineralSubstances(), result.getMineralSubstances());
    assertEquals(expected.getSodium(), result.getSodium());
    assertEquals(expected.getMagnesium(), result.getMagnesium());
    assertEquals(expected.getPhosphorus(), result.getPhosphorus());
    assertEquals(expected.getPotassium(), result.getPotassium());
    assertEquals(expected.getCalcium(), result.getCalcium());
    assertEquals(expected.getIodine(), result.getIodine());
    assertEquals(expected.getVitaminB2(), result.getVitaminB2());
    assertEquals(expected.getNiacinVitaminPP(), result.getNiacinVitaminPP());
    assertEquals(expected.getNiacinEquivalent(), result.getNiacinEquivalent());
    assertEquals(expected.getVitaminB6(), result.getVitaminB6());
    assertEquals(expected.getAlcohol(), result.getAlcohol());
    assertEquals(expected.getEnergyKj(), result.getEnergyKj());
    assertEquals(expected.getIron(), result.getIron());
    assertEquals(expected.getVitaminB1(), result.getVitaminB1());
    assertEquals(expected.getZinc(), result.getZinc());
    assertEquals(expected.getSaturatedFattyAcids(), result.getSaturatedFattyAcids());
    assertEquals(expected.getMonounsaturatedFattyAcids(), result.getMonounsaturatedFattyAcids());
    assertEquals(expected.getPolyunsaturatedFattyAcids(), result.getPolyunsaturatedFattyAcids());
    assertEquals(expected.getStarch(), result.getStarch());
    assertEquals(expected.getFiberMaterials(), result.getFiberMaterials());
    assertEquals(expected.getSelenium(), result.getSelenium());
    assertEquals(expected.getVitaminARetinol(), result.getVitaminARetinol());
    assertEquals(expected.getVitaminETocopherol(), result.getVitaminETocopherol());
    assertEquals(expected.getFolicAcid(), result.getFolicAcid());
    assertEquals(expected.getVitaminC(), result.getVitaminC());
    assertEquals(expected.getAnimalProtein(), result.getAnimalProtein());
    assertEquals(expected.getCholesterol(), result.getCholesterol());
    assertEquals(expected.getVitaminD(), result.getVitaminD());
    assertEquals(expected.getVitaminB12(), result.getVitaminB12());
    assertEquals(expected.getSugar(), result.getSugar());
  }


  @Test
  void productMapToProductNamesDto() {
    Product given = ProductMother.getProduct();
    ProductsNamesDto expected = ProductMother.getProductsNamesDto();
    ProductsNamesDto result = productMapper.productMapToProductNamesDto(given);
    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getUuid(), result.getUuid());
  }

  @Test
  void mapToProductForUpdate() {
    Product given = ProductMother.getProduct();
    ProductDto givenDto = ProductMother.getProductDto();
    Product result = productMapper.mapToProductForUpdate(givenDto, given);
    assertEquals(given.getId(), result.getId());
  }
}
