package eu.kiminiuslt.bdsm.menu.service;

import eu.kiminiuslt.bdsm.menu.mapper.MenuDayMapper;
import eu.kiminiuslt.bdsm.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.menu.repository.MenuDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
  private final MenuDayRepository menuDayRepository;
  private final MenuDayMapper menuDayMapper;

  public MenuDto getMenu() {
    return MenuDto.builder().daysList(getAllDayList()).build();
  }

  private List<MenuDayDto> getAllDayList() {
    return menuDayRepository.findAll().stream()
        .map(menuDayMapper::entityToDto)
        .collect(Collectors.toList());
  }

  public void save(MenuDto menu) {}

  public void delete() {
    menuDayRepository.deleteById(1);
  }
}
