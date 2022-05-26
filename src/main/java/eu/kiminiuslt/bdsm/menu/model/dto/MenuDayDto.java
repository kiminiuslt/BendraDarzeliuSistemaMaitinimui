package eu.kiminiuslt.bdsm.menu.model.dto;

import eu.kiminiuslt.bdsm.recipe.model.dto.RecipeDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MenuDayDto {
  private Integer id;
  private Integer dayNumber;
  private Set<RecipeDto> dayRecipesDto;

  public Double getDayEnergyValue() {
    return dayRecipesDto.stream().mapToDouble(RecipeDto::allEnergyValueKcal).sum();
  }
}
