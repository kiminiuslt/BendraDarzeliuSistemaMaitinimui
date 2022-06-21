package eu.kiminiuslt.bdsm.core.recipe.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RecipeNamesDto {
  private UUID uuid;
  private String recipeName;
  private Double allProteins;
  private Double allFat;
  private Double allCarbohydrates;
  private Double allEnergyValueKcal;
  private Double allProteinsLittleOnes;
  private Double allFatLittleOnes;
  private Double allCarbohydratesLittleOnes;
  private Double allEnergyValueKcalLittleOnes;
}
