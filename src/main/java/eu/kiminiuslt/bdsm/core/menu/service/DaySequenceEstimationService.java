package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.DayShortageDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.ProductShortageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DaySequenceEstimationService {

  public Double getOtherDaysRequiredGrams(
      ProductShortageDto productShortageDto, List<DayShortageDto> alreadyCalculatedDays) {
    double result = 0.0;
    UUID productUUID = productShortageDto.getUuid();
    for (DayShortageDto alreadyCalculatedDay : alreadyCalculatedDays) {
      for (int j = 0; j < alreadyCalculatedDay.getProductsShortageDtoList().size(); j++) {
        if (alreadyCalculatedDay.getProductsShortageDtoList().get(j).getUuid().equals(productUUID))
          result =
              result + alreadyCalculatedDay.getProductsShortageDtoList().get(j).getRequiredGrams();
      }
    }
    return result;
  }
}
