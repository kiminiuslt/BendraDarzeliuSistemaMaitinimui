package eu.kiminiuslt.bdsm.unit.recipe;

import eu.kiminiuslt.bdsm.core.product.service.ProductService;
import eu.kiminiuslt.bdsm.core.recipe.mapper.ProductAndQuantityMapper;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.jpa.entity.ProductAndQuantity;
import eu.kiminiuslt.bdsm.unit.product.ProductMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductAndQuantityMapperTest {

  @Mock private ProductService productService;

  @InjectMocks private ProductAndQuantityMapper productAndQuantityMapper;

  @Test
  void dtoToEntity() {
    Set<ProductAndQuantityDto> given = ProductAndQuantityMother.getDtoList();
    Set<ProductAndQuantity> expected = ProductAndQuantityMother.getEntityList();
    when(productService.getProductByUUID(ProductMother.getProduct().getUuid()))
        .thenReturn(ProductMother.getProduct());

    Set<ProductAndQuantity> result = productAndQuantityMapper.dtoToEntity(given);
    List<ProductAndQuantity> expectedList = new ArrayList<>(expected);
    List<ProductAndQuantity> resultList = new ArrayList<>(result);

    for (ProductAndQuantity andQuantity : resultList) {
      for (ProductAndQuantity productAndQuantity : expectedList) {
        if (andQuantity.getId().equals(productAndQuantity.getId())) {
          assertObjectsEntity(andQuantity, productAndQuantity);
        }
      }
    }
    verify(productService, times(10)).getProductByUUID(ProductMother.getProduct().getUuid());
  }

  @Test
  void entityToDto() {
    Set<ProductAndQuantity> given = ProductAndQuantityMother.getEntityList();
    Set<ProductAndQuantityDto> expected = ProductAndQuantityMother.getDtoList();
    when(productService.getProductById(ProductMother.getProduct().getId()))
        .thenReturn(ProductMother.getProduct());

    Set<ProductAndQuantityDto> result = productAndQuantityMapper.entityToDto(given);
    List<ProductAndQuantityDto> expectedList = new ArrayList<>(expected);
    List<ProductAndQuantityDto> resultList = new ArrayList<>(result);

    for (ProductAndQuantityDto andQuantity : resultList) {
      for (ProductAndQuantityDto productAndQuantity : expectedList) {
        if (andQuantity.getId().equals(productAndQuantity.getId())) {
          assertObjectsDto(andQuantity, productAndQuantity);
        }
      }
    }
    verify(productService, times(10)).getProductById(ProductMother.getProduct().getId());
  }

  private void assertObjectsDto(ProductAndQuantityDto result, ProductAndQuantityDto expected) {
    assertEquals(expected.getProductUUID(), result.getProductUUID());
    assertEquals(expected.getQuantityGross(), result.getQuantityGross());
    assertEquals(expected.getQuantityNet(), result.getQuantityNet());
    assertEquals(expected.getQuantityGrossLittleOnes(), result.getQuantityGrossLittleOnes());
    assertEquals(expected.getQuantityNetLittleOnes(), result.getQuantityNetLittleOnes());
  }

  private void assertObjectsEntity(ProductAndQuantity result, ProductAndQuantity expected) {
    assertEquals(expected.getProductId(), result.getProductId());
    assertEquals(expected.getQuantityGross(), result.getQuantityGross());
    assertEquals(expected.getQuantityNet(), result.getQuantityNet());
    assertEquals(expected.getQuantityGrossLittleOnes(), result.getQuantityGrossLittleOnes());
    assertEquals(expected.getQuantityNetLittleOnes(), result.getQuantityNetLittleOnes());
  }
}
