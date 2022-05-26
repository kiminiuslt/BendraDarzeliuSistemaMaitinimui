package eu.kiminiuslt.bdsm.warehouse.mapper;

import eu.kiminiuslt.bdsm.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;
import eu.kiminiuslt.bdsm.product.repository.ProductRepository;
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
}
