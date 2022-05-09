package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.model.entity.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public void addProduct(ProductDto product) {

    productRepository.save(
        Product.builder()
            .uuid(UUID.randomUUID())
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
            .monounsaturatedFattyAcids(product.getSaturatedFattyAcids())
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
            .energyValueKcal(product.getEnergyValueKcal())
            .sugar(product.getSugar())
            .build());
  }

  public Page<ProductDto> getPageableProduct(Pageable pageable) {
    return productRepository.findAll(pageable).map(productMapper::mapToProductDto);
  }

  public List<ProductForRecipeDto> getProductsList() {
    return productRepository.findAll().stream()
        .map(productMapper::productMapToProductForRecipeDto)
        .collect(Collectors.toList());
  }

  public ProductDto getProductByUUID(UUID id) {
    return productMapper.mapToProductDto(productRepository.findByUuid(id));
  }

  @Transactional
  public void updateProduct(ProductDto productDto) {
    Product product =
        productRepository.findByUuid(productDto.getUuid()).toBuilder()
            .name(productDto.getName())
            .carbohydrates(productDto.getCarbohydrates())
            .energyValueKcal(productDto.getEnergyValueKcal())
            .build();

    productRepository.save(product);
  }

  @Transactional
  public void deleteProduct(UUID id) {
    productRepository.deleteById(productRepository.findByUuid(id).getId());
  }
}
