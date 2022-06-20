package eu.kiminiuslt.bdsm.core.menu.model.dto;

import eu.kiminiuslt.bdsm.core.recipe.model.dto.RecipeDto;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDayDto {
  private Integer id;
  private Integer dayNumber;
  private Double dayEnergyValue;
  private Double dayEnergyValueLittleOnes;
  private Set<RecipeDto> dayRecipesDto;
  private List<ProductShortageDto> productShortage;
}
