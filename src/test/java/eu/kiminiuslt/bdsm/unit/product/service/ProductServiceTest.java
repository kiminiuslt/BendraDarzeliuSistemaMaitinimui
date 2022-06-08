package eu.kiminiuslt.bdsm.unit.product.service;

import eu.kiminiuslt.bdsm.product.mapper.ProductMapper;
import eu.kiminiuslt.bdsm.unit.product.model.ProductMother;
import eu.kiminiuslt.bdsm.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductForRecipeDto;
import eu.kiminiuslt.bdsm.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.product.model.entity.Product;
import eu.kiminiuslt.bdsm.product.repository.ProductRepository;
import eu.kiminiuslt.bdsm.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock private ProductRepository productRepository;

  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductService productService;

  @Test
  void addProduct() {
    ProductDto given = ProductMother.getProductDto();
    Product result = ProductMother.getProduct();
    Mockito.when(productMapper.mapToProduct(eq(given))).thenReturn(result);
    productService.addProduct(given);
    verify(productRepository, times(1)).save(result);
  }

  @Test
  void getPageableProduct() {
    ProductDto result = ProductMother.getProductDto();
    Product given = ProductMother.getProduct();
    when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(given)));
    when(productMapper.mapToProductDto(given)).thenReturn(result);
    Page<ProductDto> resultList = productService.getPageableProduct(PageRequest.of(1, 1));
    Assertions.assertEquals(result.getName(), resultList.getContent().get(0).getName());
    verify(productMapper, times(1)).mapToProductDto(given);
    verify(productRepository, times(1)).findAll(any(Pageable.class));
  }

  @Test
  void getProductsListRecipeDto() {
    Product given = ProductMother.getProduct();
    ProductForRecipeDto result = ProductMother.getProductForRecipeDto();
    when(productMapper.productMapToProductForRecipeDto(given)).thenReturn(result);
    when(productRepository.findAll()).thenReturn(List.of(given));
    List<ProductForRecipeDto> resultList = productService.getProductsListRecipeDto();
    Assertions.assertEquals(result.getName(), resultList.get(0).getName());
    verify(productMapper, times(1)).productMapToProductForRecipeDto(given);
    verify(productRepository, times(1)).findAll();
  }

  @Test
  void getProductsListNamesDto() {
    Product given = ProductMother.getProduct();
    ProductsNamesDto result = ProductMother.getProductForRecipeDto();
    when(productMapper.productMapToProductNamesDto(given)).thenReturn(result);
    when(productRepository.findAll()).thenReturn(List.of(given));
    List<ProductsNamesDto> resultList = productService.getProductsListNamesDto();
    Assertions.assertEquals(result.getName(), resultList.get(0).getName());
    verify(productMapper, times(1)).productMapToProductNamesDto(given);
    verify(productRepository, times(1)).findAll();
  }

  @Test
  void getProductsList() {
    Product given = ProductMother.getProduct();
    List<Product> result = List.of(given);
    when(productRepository.findAll()).thenReturn(List.of(given));
    List<Product> resultList = productService.getProductsList();
    Assertions.assertEquals(result.get(0).getName(), resultList.get(0).getName());
    verify(productRepository, times(1)).findAll();
  }

  @Test
  void getProductDtoByUUID_WhenUuidExist() {
    Product given = ProductMother.getProduct();
    UUID uuid = given.getUuid();
    Mockito.when(productService.getProductByUUID(eq(uuid))).thenReturn(given);
    Product result = productService.getProductByUUID(uuid);
    Assertions.assertEquals(given, result);
    verify(productRepository, times(1)).findByUuid(uuid);
  }

  @Test
  void getProductDtoByUUID_WhenUuidNotExist() {
    UUID uuid = UUID.fromString("cc4038b9-d4a6-4830-8470-ac2b85207a18");
    Mockito.when(productService.getProductByUUID(eq(uuid))).thenReturn(null);
    Product result = productService.getProductByUUID(uuid);
    assertNull(result);
    verify(productRepository, times(1)).findByUuid(uuid);
  }

  @Test
  void getProductDtoByUUID_WhenUUIDIsNull() {
    Mockito.when(productService.getProductByUUID(eq(null))).thenReturn(null);
    Product result = productService.getProductByUUID(null);
    assertNull(result);
    verify(productRepository, times(1)).findByUuid(null);
  }

  @Test
  void getProductByUUID() {
    Product given = ProductMother.getProduct();
    UUID uuid = given.getUuid();
    Mockito.when(productRepository.findByUuid(eq(uuid))).thenReturn(given);
    Product result = productRepository.findByUuid(uuid);
    Assertions.assertEquals(given, result);
    verify(productRepository, times(1)).findByUuid(uuid);
  }

  @Test
  void updateProduct() {
    ProductDto given = ProductMother.getProductDto();
    UUID uuid = given.getUuid();
    given.setUuid(uuid);
    Product givenProduct = ProductMother.getProduct();
    Mockito.when(productRepository.findByUuid(uuid)).thenReturn(givenProduct);
    Mockito.when(productMapper.mapToProductForUpdate(given,givenProduct)).thenReturn(givenProduct);
    productService.updateProduct(given);
    verify(productRepository, times(1)).findByUuid(uuid);
    verify(productRepository, times(1)).save(givenProduct);
  }

  @Test
  void deleteProduct() {
    Product given = ProductMother.getProduct();
    UUID uuid = given.getUuid();
    Mockito.when(productRepository.findByUuid(uuid)).thenReturn(given);
    productService.deleteProduct(uuid);
    verify(productRepository, times(1)).findByUuid(uuid);
    verify(productRepository, times(1)).deleteById(given.getId());
  }

  @Test
  void getProductById() {
    Product given = ProductMother.getProduct();
    int id = given.getId();
    Mockito.when(productRepository.findById(id)).thenReturn(given);
    Product result = productService.getProductById(id);
    Assertions.assertEquals(given.getName(), result.getName());
    verify(productRepository, times(1)).findById(id);
  }
}
