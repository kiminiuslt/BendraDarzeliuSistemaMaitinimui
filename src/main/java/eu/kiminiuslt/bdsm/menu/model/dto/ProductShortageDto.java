package eu.kiminiuslt.bdsm.menu.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductShortageDto {
  private String name;
  private UUID uuid;
  private Integer productId;
  private Double requiredGrams;
  private Double ownedKg;
  private Double shortageKg;
}
