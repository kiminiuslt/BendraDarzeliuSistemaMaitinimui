package eu.kiminiuslt.bdsm.warehouse.service;

import eu.kiminiuslt.bdsm.warehouse.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;
import eu.kiminiuslt.bdsm.warehouse.repository.WarehouseRepository;
import eu.kiminiuslt.bdsm.product.service.ProductService;
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

  public void addWarehouseRecord(WarehouseDto warehouseDto) {
    warehouseRepository.save(warehouseMapper.warehouseDtoToWarehouse(warehouseDto));
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
  }

  @Transactional
  public void deleteWarehouseRecord(UUID uuid) {
    warehouseRepository.deleteById(warehouseRepository.findByUuid(uuid).getId());
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
