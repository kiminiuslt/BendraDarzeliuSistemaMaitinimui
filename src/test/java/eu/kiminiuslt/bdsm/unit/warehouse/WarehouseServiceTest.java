package eu.kiminiuslt.bdsm.unit.warehouse;

import eu.kiminiuslt.bdsm.core.history.HistoryService;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.core.product.service.ProductService;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import eu.kiminiuslt.bdsm.unit.product.ProductMother;
import eu.kiminiuslt.bdsm.core.warehouse.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.jpa.entity.Warehouse;
import eu.kiminiuslt.bdsm.jpa.repository.WarehouseRepository;
import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

  @Mock private WarehouseRepository warehouseRepository;

  @Mock private WarehouseMapper warehouseMapper;

  @Mock private ProductService productService;

  @Mock private HistoryService historyService;

  @InjectMocks private WarehouseService warehouseService;

  @Test
  void addWarehouseRecord() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse result = WarehouseMother.getWarehouse();
    when(warehouseMapper.warehouseDtoToWarehouse(given)).thenReturn(result);
    warehouseService.addWarehouseRecord(given);
    verify(warehouseMapper, times(1)).warehouseDtoToWarehouse(given);
    verify(warehouseRepository, times(1)).save(result);
    verify(historyService, times(1))
        .historySavedWarehouseRecord(given.getProductName(), given.getAmount());
  }

  @Test
  void getWarehouseList() {
    Warehouse given = WarehouseMother.getWarehouse();
    WarehouseDto result = WarehouseMother.getWarehouseDto();
    when(warehouseMapper.warehouseToWarehouseDto(given)).thenReturn(result);
    when(warehouseRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(given)));
    Page<WarehouseDto> resultList = warehouseService.getWarehouseList(PageRequest.of(1, 1));
    assertEquals(result.getUuid(), resultList.getContent().get(0).getUuid());
    assertEquals(result.getProductName(), resultList.getContent().get(0).getProductName());
    assertEquals(result.getAmount(), resultList.getContent().get(0).getAmount());
    assertEquals(result.getInvoice(), resultList.getContent().get(0).getInvoice());
    assertEquals(result.getProductId(), resultList.getContent().get(0).getProductId());
    assertEquals(result.getWriteOff(), resultList.getContent().get(0).getWriteOff());
    verify(warehouseMapper, times(1)).warehouseToWarehouseDto(given);
    verify(warehouseRepository, times(1)).findAll(any(Pageable.class));
  }

  @Test
  void getAllProductsNames() {
    List<ProductsNamesDto> given = List.of(ProductMother.getProductsNamesDto());
    when(productService.getProductsListNamesDto()).thenReturn(given);
    List<ProductsNamesDto> resultList = warehouseService.getAllProductsNames();
    assertEquals(given.get(0).getName(), resultList.get(0).getName());
    assertEquals(given.get(0).getUuid(), resultList.get(0).getUuid());
    verify(productService, times(1)).getProductsListNamesDto();
  }

  @Test
  void getWarehouseDtoRecordByUUID() {
    Warehouse given = WarehouseMother.getWarehouse();
    WarehouseDto resultExpected = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    when(warehouseRepository.findByUuid(uuid)).thenReturn(given);
    when(warehouseMapper.warehouseToWarehouseDto(given)).thenReturn(resultExpected);
    WarehouseDto result = warehouseService.getWarehouseDtoRecordByUUID(uuid);
    assertEquals(given.getUuid(), result.getUuid());
    assertEquals(given.getAmount(), result.getAmount());
    assertEquals(given.getInvoice(), result.getInvoice());
    assertEquals(given.getProductId(), result.getProductId());
    verify(warehouseRepository, times(1)).findByUuid(uuid);
    verify(warehouseMapper, times(1)).warehouseToWarehouseDto(given);
  }

  @Test
  void updateWarehouse() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse givenWarehouse = WarehouseMother.getWarehouse();
    when(warehouseRepository.findByUuid(given.getUuid())).thenReturn(givenWarehouse);
    when(warehouseMapper.warehouseDtoToWarehouseForUpdate(given, givenWarehouse))
        .thenReturn(givenWarehouse);
    warehouseService.updateWarehouse(given);
    verify(warehouseMapper, times(1)).warehouseDtoToWarehouseForUpdate(given, givenWarehouse);
    verify(warehouseRepository, times(1)).save(givenWarehouse);
    verify(historyService, times(1))
        .historyUpdatedWarehouseRecord(given.getProductName(), given.getAmount());
  }

  @Test
  void deleteWarehouseRecord() {
    Warehouse givenWarehouse = WarehouseMother.getWarehouse();
    Product givenProduct = ProductMother.getProduct();
    UUID uuid = givenWarehouse.getUuid();
    when(warehouseRepository.findByUuid(uuid)).thenReturn(givenWarehouse);
    when(productService.getProductById(givenWarehouse.getProductId())).thenReturn(givenProduct);
    warehouseService.deleteWarehouseRecord(uuid);
    verify(warehouseRepository, times(1)).findByUuid(uuid);
    verify(warehouseRepository, times(1)).deleteById(givenWarehouse.getId());
    verify(historyService, times(1)).historyDeletedWarehouseRecord(givenProduct.getName());
  }

  @Test
  void writeOff_WhenAmountEqualsWriteOff() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse givenWarehouse = WarehouseMother.getWarehouse();
    Product givenProduct = ProductMother.getProduct();
    UUID uuid = given.getUuid();
    double givenAmount = 13.8;
    when(warehouseRepository.findByUuid(uuid)).thenReturn(givenWarehouse);
    when(warehouseService.getWarehouseDtoRecordByUUID(uuid)).thenReturn(given);
    when(productService.getProductById(givenWarehouse.getProductId())).thenReturn(givenProduct);
    warehouseService.writeOff(givenAmount, uuid);
    verify(warehouseRepository, times(1)).deleteById(givenWarehouse.getId());
    verify(historyService, times(1)).historyDeletedWarehouseRecord(givenProduct.getName());
  }

  @Test
  void writeOff_WhenAmountLessThanWriteOff() {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    Warehouse givenWarehouse = WarehouseMother.getWarehouse();
    UUID uuid = given.getUuid();
    double givenAmount = 10.8;
    when(warehouseRepository.findByUuid(uuid)).thenReturn(givenWarehouse);
    when(warehouseMapper.warehouseDtoToWarehouseForUpdate(given, givenWarehouse))
        .thenReturn(givenWarehouse);
    when(warehouseService.getWarehouseDtoRecordByUUID(uuid)).thenReturn(given);
    warehouseService.writeOff(givenAmount, uuid);
    verify(warehouseMapper, times(1)).warehouseDtoToWarehouseForUpdate(given, givenWarehouse);
    verify(warehouseRepository, times(1)).save(givenWarehouse);
    verify(historyService, times(1))
        .historyUpdatedWarehouseRecord(given.getProductName(), given.getAmount());
  }

  @Test
  void getWarehouseById() {
    Warehouse given = WarehouseMother.getWarehouse();
    Integer id = given.getId();
    when(warehouseRepository.findByProductId(id)).thenReturn(given);
    Warehouse result = warehouseService.getWarehouseById(id);
    assertEquals(given.getId(), result.getId());
    assertEquals(given.getProductId(), result.getProductId());
    assertEquals(given.getUuid(), result.getUuid());
    assertEquals(given.getAmount(), result.getAmount());
    assertEquals(given.getInvoice(), result.getInvoice());
    verify(warehouseRepository, times(1)).findByProductId(id);
  }

  @Test
  void getEmptyWarehouseDto() {
    WarehouseDto given = WarehouseDto.builder().build();
    WarehouseDto result = warehouseService.getEmptyWarehouseDto();
    assertEquals(given.getProductId(), result.getProductId());
    assertEquals(given.getUuid(), result.getUuid());
    assertEquals(given.getAmount(), result.getAmount());
    assertEquals(given.getInvoice(), result.getInvoice());
    assertEquals(given.getProductName(), result.getProductName());
    assertEquals(given.getWriteOff(), result.getWriteOff());
  }
}
