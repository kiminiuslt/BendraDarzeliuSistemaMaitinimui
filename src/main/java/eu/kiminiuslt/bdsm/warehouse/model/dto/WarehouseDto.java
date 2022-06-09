package eu.kiminiuslt.bdsm.warehouse.model.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseDto {
  private UUID uuid;

  @NotBlank(message = "{warehouse.validate.name.blank}")
  private String productName;

  @Min(value = 1, message = "{warehouse.validate.name}")
  private double amount;

  private String invoice;

  private Integer productId;

  private double writeOff;
}
