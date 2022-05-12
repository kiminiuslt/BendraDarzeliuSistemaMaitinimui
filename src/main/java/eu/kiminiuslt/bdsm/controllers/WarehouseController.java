package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.helpers.MessageService;
import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

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

  @GetMapping("/warehouse-record")
  public String newWarehouseRecord(Model model, String message) {
    model.addAttribute("warehouseDto", WarehouseDto.builder().build());
    model.addAttribute("allProductsList", warehouseService.getAllProductsNames());
    model.addAttribute("message", messageService.getMessage(message));
    return "warehouse/warehouse-record";
  }

  @PostMapping("/warehouse-record")
  public String saveWarehouseRecord(@Valid WarehouseDto warehouseDto, BindingResult errors) {
    if (errors.hasErrors()) {
      return "warehouse/warehouse-record";
    }
    return "redirect:/warehouse/warehouse-record?message=warehouse.create.success.message";
  }
}
