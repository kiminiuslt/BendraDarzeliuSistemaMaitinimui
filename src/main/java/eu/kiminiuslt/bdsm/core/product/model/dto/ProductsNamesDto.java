package eu.kiminiuslt.bdsm.core.product.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductsNamesDto {

  @NotBlank(message = "{validate.name.blank}")
  private String name;

  private UUID uuid;
}
