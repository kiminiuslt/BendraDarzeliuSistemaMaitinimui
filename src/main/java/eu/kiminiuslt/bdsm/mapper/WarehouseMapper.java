package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.model.entity.Warehouse;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {
  private final ProductRepository productRepository;

  public WarehouseDto warehouseToWarehouseDto(Warehouse warehouse) {
    return WarehouseDto.builder()
        .id(warehouse.getId())
        .uuid(warehouse.getUuid())
        .amount(warehouse.getAmount())
        .invoice(warehouse.getInvoice())
        .productName(productRepository.findById(warehouse.getProductId()).getName())
        .build();
  }

  public Warehouse warehouseDtoToWarehouse(WarehouseDto warehousedto) {
    return Warehouse.builder()
        .id(warehousedto.getId())
        .uuid(UUID.randomUUID())
        .amount(warehousedto.getAmount())
        .invoice(warehousedto.getInvoice())
// TODO: FIX PRODUCT NAME SELECTION (IN FRONT OR BACK)
        .productId(productRepository.findByName(warehousedto.getProductName()).getId())
        .build();
  }
}
