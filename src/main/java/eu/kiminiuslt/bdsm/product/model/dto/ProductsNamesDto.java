package eu.kiminiuslt.bdsm.product.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsNamesDto {
  private UUID uuid;
  private String name;
}
