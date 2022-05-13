package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.repository.WarehouseRepository;
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
        warehouseRepository.findByUuid(warehouseDto.getUuid()).toBuilder()
            .amount(warehouseDto.getAmount())
            .invoice(warehouseDto.getInvoice())
            .build());
    //    TODO: FIX PRODUCT NAME ABILITY TO BE CHANGED
  }

  @Transactional
  public void deleteWarehouseRecord(UUID uuid) {
    warehouseRepository.deleteById(warehouseRepository.findByUuid(uuid).getId());
  }
}
