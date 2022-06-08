package eu.kiminiuslt.bdsm.unit.warehouse;

import eu.kiminiuslt.bdsm.product.model.entity.Product;
import eu.kiminiuslt.bdsm.product.repository.ProductRepository;
import eu.kiminiuslt.bdsm.unit.product.ProductMother;
import eu.kiminiuslt.bdsm.warehouse.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseMapperTest {
  @Mock private ProductRepository productRepository;

  @InjectMocks WarehouseMapper warehouseMapper;

  @Test
  void warehouseToWarehouseDto() {
    Warehouse given = WarehouseMother.getWarehouse();
    WarehouseDto expected = WarehouseMother.getWarehouseDto();
    Product givenProduct = ProductMother.getProduct();
    when(productRepository.findById(given.getProductId())).thenReturn(givenProduct);
    WarehouseDto result = warehouseMapper.warehouseToWarehouseDto(given);
    assertEquals(expected.getUuid(), result.getUuid());
    assertEquals(expected.getAmount(), result.getAmount());
    assertEquals(expected.getInvoice(), result.getInvoice());
    assertEquals(expected.getProductId(), result.getProductId());
    assertEquals(expected.getProductName(), result.getProductName());
    verify(productRepository, times(1)).findById(given.getProductId());
  }

  @Test
  void warehouseDtoToWarehouse() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse expected = WarehouseMother.getWarehouse();
    Warehouse result = warehouseMapper.warehouseDtoToWarehouse(given);
    assertEquals(expected.getAmount(), result.getAmount());
    assertEquals(expected.getInvoice(), result.getInvoice());
    assertEquals(expected.getProductId(), result.getProductId());
  }

  @Test
  void warehouseDtoToWarehouseForUpdate() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse givenWarehouse = WarehouseMother.getWarehouse();
    Warehouse expected = WarehouseMother.getWarehouse();
    Warehouse result = warehouseMapper.warehouseDtoToWarehouseForUpdate(given, givenWarehouse);
    assertEquals(givenWarehouse.getId(), result.getId());
    assertEquals(expected.getAmount(), result.getAmount());
    assertEquals(expected.getInvoice(), result.getInvoice());
    assertEquals(expected.getProductId(), result.getProductId());
  }
}
