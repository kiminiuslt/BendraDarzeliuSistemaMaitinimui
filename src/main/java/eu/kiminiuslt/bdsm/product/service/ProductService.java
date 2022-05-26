package eu.kiminiuslt.bdsm.product.service;

import eu.kiminiuslt.bdsm.product.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.recipe.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.product.model.entity.Product;
import eu.kiminiuslt.bdsm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public void addProduct(ProductDto productDto) {
    productRepository.save(productMapper.mapToProduct(productDto));
  }

  public Page<ProductDto> getPageableProduct(Pageable pageable) {
    return productRepository.findAll(pageable).map(productMapper::mapToProductDto);
  }

  public List<ProductForRecipeDto> getProductsListRecipeDto() {
    return getProductsList().stream()
        .map(productMapper::productMapToProductForRecipeDto)
        .sorted(Comparator.comparing(ProductForRecipeDto::getName))
        .collect(Collectors.toList());
  }

  public List<ProductsNamesDto> getProductsListNamesDto() {
    return getProductsList().stream()
        .map(productMapper::productMapToProductNamesDto)
        .sorted(Comparator.comparing(ProductsNamesDto::getName))
        .collect(Collectors.toList());
  }

  public List<Product> getProductsList() {
    return new ArrayList<>(productRepository.findAll());
  }

  public ProductDto getProductDtoByUUID(UUID id) {
    return productMapper.mapToProductDto(productRepository.findByUuid(id));
  }

  public Product getProductByUUID(UUID uuid) {
    return productRepository.findByUuid(uuid);
  }

  @Transactional
  public void updateProduct(ProductDto productDto) {
    productRepository.save(
        productRepository.findByUuid(productDto.getUuid()).toBuilder()
            .name(productDto.getName())
            .carbohydrates(productDto.getCarbohydrates())
            .energyValueKcal(productDto.getEnergyValueKcal())
            .build());
  }

  @Transactional
  public void deleteProduct(UUID id) {
    productRepository.deleteById(productRepository.findByUuid(id).getId());
  }

  public Page<ProductDto> getProductByNamePageable(String findProductName, Pageable pageable) {
    return productRepository
        .findProductsByNameIsLike(convertToLikeResult(findProductName), pageable)
        .map(productMapper::mapToProductDto);
  }

  public Product getProductById(int id) {
    return productRepository.findById(id);
  }

  private String convertToLikeResult(String value) {
    return '%' + value + '%';
  }
}
