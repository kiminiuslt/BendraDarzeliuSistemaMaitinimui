package eu.kiminiuslt.bdsm.menu.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
  private List<MenuDayDto> daysList;
}
