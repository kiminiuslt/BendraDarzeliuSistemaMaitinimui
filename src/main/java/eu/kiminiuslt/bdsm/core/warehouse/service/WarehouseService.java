package eu.kiminiuslt.bdsm.core.warehouse.service;

import eu.kiminiuslt.bdsm.core.history.HistoryService;
import eu.kiminiuslt.bdsm.core.warehouse.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
import eu.kiminiuslt.bdsm.jpa.entity.Warehouse;
import eu.kiminiuslt.bdsm.jpa.repository.WarehouseRepository;
import eu.kiminiuslt.bdsm.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {
  private final WarehouseRepository warehouseRepository;
  private final WarehouseMapper warehouseMapper;
  private final ProductService productService;
  private final HistoryService historyService;

  public void addWarehouseRecord(WarehouseDto warehouseDto) {
    warehouseRepository.save(warehouseMapper.warehouseDtoToWarehouse(warehouseDto));
    historyService.historySavedWarehouseRecord(warehouseDto.getProductName(),warehouseDto.getAmount());
  }

  public Page<WarehouseDto> getWarehouseList(Pageable pageable) {
    return warehouseRepository.findAll(pageable).map(warehouseMapper::warehouseToWarehouseDto);
  }

  public List<ProductsNamesDto> getAllProductsNames() {
    return productService.getProductsListNamesDto();
  }

  public WarehouseDto getWarehouseDtoRecordByUUID(UUID uuid) {
    return warehouseMapper.warehouseToWarehouseDto(warehouseRepository.findByUuid(uuid));
  }

  @Transactional
  public void updateWarehouse(WarehouseDto warehouseDto) {
    warehouseRepository.save(
        warehouseMapper.warehouseDtoToWarehouseForUpdate(
            warehouseDto, warehouseRepository.findByUuid(warehouseDto.getUuid())));
    historyService.historyUpdatedWarehouseRecord(warehouseDto.getProductName(),warehouseDto.getAmount());
  }

  @Transactional
  public void deleteWarehouseRecord(UUID uuid) {
    Warehouse warehouse = warehouseRepository.findByUuid(uuid);
    Product product = productService.getProductById(warehouse.getProductId());
    warehouseRepository.deleteById(warehouse.getId());
    historyService.historyDeletedWarehouseRecord(product.getName());
  }

  public void writeOff(double writeOff, UUID uuid) {
    WarehouseDto warehouseDto = getWarehouseDtoRecordByUUID(uuid);
    if (writeOff > 0 && warehouseDto.getAmount() >= writeOff) {
      if (warehouseDto.getAmount() == writeOff) {
        deleteWarehouseRecord(warehouseDto.getUuid());
      } else {
        warehouseDto.setAmount(warehouseDto.getAmount() - writeOff);
        updateWarehouse(warehouseDto);
      }
    }
  }

  public Warehouse getWarehouseById(Integer id) {
    return warehouseRepository.findByProductId(id);
  }

  public WarehouseDto getEmptyWarehouseDto() {
    return WarehouseDto.builder().build();
  }
}
