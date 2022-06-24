package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.DayShortageDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.ProductShortageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortageFilterService {

  public List<DayShortageDto> removeWhatIsNotShortage(List<DayShortageDto> dayShortageList) {
    List<DayShortageDto> result = new ArrayList<>();
    for (DayShortageDto dayShortageDto : dayShortageList) {
      dayShortageDto.setProductsShortageDtoList(
          filterShortageDto(dayShortageDto.getProductsShortageDtoList()));
      result.add(dayShortageDto);
    }
    return result;
  }

  private List<ProductShortageDto> filterShortageDto(
      List<ProductShortageDto> productsShortageDtoList) {
    return productsShortageDtoList.stream()
        .filter(o -> o.getShortageKg() >= 0)
        .collect(Collectors.toList());
  }
}
