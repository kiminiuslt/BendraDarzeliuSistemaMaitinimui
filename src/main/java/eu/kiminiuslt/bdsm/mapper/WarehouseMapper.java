package eu.kiminiuslt.bdsm.mapper;

import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.model.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseMapper {

  public WarehouseDto warehouseToWarehouseDto(Warehouse warehouse) {
    return WarehouseDto.builder()
        .id(warehouse.getId())
        .uuid(warehouse.getUuid())
        .amount(warehouse.getAmount())
        .invoice(warehouse.getInvoice())
        .productID(warehouse.getProductId())
        .build();
  }

  public Warehouse warehouseDtoToWarehouse(WarehouseDto warehousedto) {
    return Warehouse.builder()
        .id(warehousedto.getId())
        .uuid(UUID.randomUUID())
        .amount(warehousedto.getAmount())
        .invoice(warehousedto.getInvoice())
        .productId(warehousedto.getProductID())
        .build();
  }
}
