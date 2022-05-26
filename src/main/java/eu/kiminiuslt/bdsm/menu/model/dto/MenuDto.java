package eu.kiminiuslt.bdsm.menu.model.dto;

import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
  private List<MenuDay> daysList;
}
