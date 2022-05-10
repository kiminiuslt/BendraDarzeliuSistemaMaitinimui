package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.model.dto.ProductForRecipeDto;
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

  public void addProduct(ProductDto productDto) {
    productRepository.save(productMapper.mapToProduct(productDto));
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

  private String convertToLikeResult(String value) {
    return '%' + value + '%';
  }
}
