package eu.kiminiuslt.bdsm.core.warehouse.mapper;

import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.jpa.entity.Warehouse;
import eu.kiminiuslt.bdsm.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {
  private final ProductRepository productRepository;

  public WarehouseDto warehouseToWarehouseDto(Warehouse warehouse) {
    return WarehouseDto.builder()
        .uuid(warehouse.getUuid())
        .amount(warehouse.getAmount())
        .invoice(warehouse.getInvoice())
        .productId(warehouse.getProductId())
        .productName(productRepository.findById(warehouse.getProductId()).getName())
        .build();
  }

  public Warehouse warehouseDtoToWarehouse(WarehouseDto warehousedto) {
    return Warehouse.builder()
        .uuid(UUID.randomUUID())
        .amount(warehousedto.getAmount())
        .invoice(warehousedto.getInvoice())
        .productId(productRepository.findByName(warehousedto.getProductName()).getId())
        .build();
  }

  public Warehouse warehouseDtoToWarehouseForUpdate(
      WarehouseDto warehouseDto, Warehouse warehouse) {
    Warehouse result = warehouseDtoToWarehouse(warehouseDto);
    result.setId(warehouse.getId());
    return result;
  }
}