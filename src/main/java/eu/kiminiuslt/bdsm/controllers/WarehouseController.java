package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {
  private final WarehouseService warehouseService;

  @GetMapping
  public String warehouseStart(
      Model model,
      @PageableDefault(
              size = 15,
              sort = {"productId"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    model.addAttribute("warehousePages", warehouseService.getWarehouseList(pageable));
    return "/warehouse/warehouse-all";
  }
}
