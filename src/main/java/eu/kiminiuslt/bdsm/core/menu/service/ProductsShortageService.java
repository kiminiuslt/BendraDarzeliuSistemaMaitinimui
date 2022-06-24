package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsShortageService {
  private final ShortageFilterService shortageFilterService;
  private final ProductShortageProvider productShortageProvider;

  public List<DayShortageDto> getProductsShortageList(
      MenuDto menuDto, List<PeopleCountDto> peopleCountDto) {

    List<DayShortageDto> result = new ArrayList<>();

    List<PeopleCountDto> sortedPeopleCountDto =
        peopleCountDto.stream()
            .sorted(Comparator.comparing(PeopleCountDto::getDayOfMenu))
            .collect(Collectors.toList());

    for (PeopleCountDto countDto : sortedPeopleCountDto) {
      int dayNumber = countDto.getDayOfMenu();
      menuDto.getDaysList().stream()
          .filter(o -> o.getDayNumber().equals(dayNumber))
          .findAny()
          .ifPresent(
              dayOfCalculations ->
                  result.add(dayShortageCalculations(dayOfCalculations, result, countDto)));
    }

    return shortageFilterService.removeWhatIsNotShortage(result);
  }

  private DayShortageDto dayShortageCalculations(
      MenuDayDto dayOfCalculations,
      List<DayShortageDto> alreadyCalculatedDays,
      PeopleCountDto peopleCountDto) {

    return DayShortageDto.builder()
        .dayNumber(dayOfCalculations.getDayNumber())
        .productsShortageDtoList(
            productShortageProvider.getDayShortage(
                dayOfCalculations, alreadyCalculatedDays, peopleCountDto))
        .build();
  }
}
