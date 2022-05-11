package eu.kiminiuslt.bdsm.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WarehouseDto {
  private int id;
  private UUID uuid;
  private int productID;
  private double amount;
  private String invoice;
}
