package eu.kiminiuslt.bdsm.core.product.service;

import eu.kiminiuslt.bdsm.core.product.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import eu.kiminiuslt.bdsm.jpa.repository.ProductRepository;
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

  /***
   * Saves Object to database
   * @param productDto object to save after verification.
   * @since 1.0.0
   */
  public void addProduct(ProductDto productDto) {
    productRepository.save(productMapper.mapToProduct(productDto));
  }

  /***
   * Returns Page instance of Products objects by Pageable params
   * @see org.springframework.data.domain.Page
   * @see org.springframework.data.domain.Pageable
   * @param pageable not null
   * @return Page of ProductsDto
   *  @since 1.0.0
   */
  public Page<ProductDto> getPageableProduct(Pageable pageable) {
    return productRepository.findAll(pageable).map(productMapper::mapToProductDto);
  }

  /***
   *
   * @return mapped and sorted ProductForRecipeDto list
   */
  public List<ProductForRecipeDto> getProductsListRecipeDto() {
    return getProductsList().stream()
        .map(productMapper::productMapToProductForRecipeDto)
        .sorted(Comparator.comparing(ProductForRecipeDto::getName))
        .collect(Collectors.toList());
  }

  /***
   *
   * @return mapped and sorted ProductsNamesDto list
   */
  public List<ProductsNamesDto> getProductsListNamesDto() {
    return getProductsList().stream()
        .map(productMapper::productMapToProductNamesDto)
        .sorted(Comparator.comparing(ProductsNamesDto::getName))
        .collect(Collectors.toList());
  }

  /***
   *
   * @return all Products object's in database
   */
  public List<Product> getProductsList() {
    return new ArrayList<>(productRepository.findAll());
  }

  /***
   * Finds Product in database by UUID and returns mapped one ProductDto object
   * @param id not null
   * @return ProductDto
   */
  public ProductDto getProductDtoByUUID(UUID id) {
    return productMapper.mapToProductDto(productRepository.findByUuid(id));
  }

  /***
   * Finds and returns Product in database by UUID
   * @param uuid not null
   * @return Product
   */
  public Product getProductByUUID(UUID uuid) {
    return productRepository.findByUuid(uuid);
  }

  /***
   * Updates existing Product in database
   * @param productDto all fields not null
   */
  @Transactional
  public void updateProduct(ProductDto productDto) {
    productRepository.save(
        productMapper.mapToProductForUpdate(
            productDto, productRepository.findByUuid(productDto.getUuid())));
  }

  /***
   * Removes Product from database by Product id
   * @param id not null
   */
  @Transactional
  public void deleteProduct(UUID id) {
    productRepository.deleteById(productRepository.findByUuid(id).getId());
  }

  /***
   * Finds and returns object by id from database
   * @param id not null
   * @return Product
   */
  public Product getProductById(int id) {
    return productRepository.findById(id);
  }

  public ProductDto getEmptyProductDto() {
    return ProductDto.builder().build();
  }
}
