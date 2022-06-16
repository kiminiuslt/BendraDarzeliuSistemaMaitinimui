package eu.kiminiuslt.bdsm.core.menu.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleCountDto {
  private Integer dayOfMenu;
  private Integer littleOnes;
  private Integer olderKids;
  private Integer workers;
}
