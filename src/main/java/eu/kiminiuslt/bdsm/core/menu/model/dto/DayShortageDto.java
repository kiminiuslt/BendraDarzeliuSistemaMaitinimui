package eu.kiminiuslt.bdsm.core.menu.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayShortageDto {
  private Integer dayNumber;
  private List<ProductShortageDto> productsShortageDtoList;
}
