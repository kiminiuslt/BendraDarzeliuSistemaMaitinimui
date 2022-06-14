package eu.kiminiuslt.bdsm.unit.warehouse;

import eu.kiminiuslt.bdsm.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;

import java.util.UUID;

public class WarehouseMother {
  private static final Integer ID = 5;
  private static final Integer PRODUCT_ID = 2;
  private static final Double AMOUNT = 13.8;
  private static final String INVOICE = "LT-1877-2002-15855EP";
  private static final String PRODUCT_NAME = "Yogurt";
  private static final UUID UU_ID = UUID.fromString("0460975c-a41a-4633-84ca-f7431e835bd9");

  public static Warehouse getWarehouse() {
    return Warehouse.builder()
        .id(ID)
        .uuid(UU_ID)
        .productId(PRODUCT_ID)
        .amount(AMOUNT)
        .invoice(INVOICE)
        .build();
  }

  public static WarehouseDto getWarehouseDto() {
    return WarehouseDto.builder()
        .uuid(UU_ID)
        .productName(PRODUCT_NAME)
        .productId(PRODUCT_ID)
        .amount(AMOUNT)
        .invoice(INVOICE)
        .build();
  }
}
