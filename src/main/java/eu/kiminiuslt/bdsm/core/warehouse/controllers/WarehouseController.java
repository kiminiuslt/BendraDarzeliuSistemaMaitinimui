package eu.kiminiuslt.bdsm.core.warehouse.controllers;

import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import eu.kiminiuslt.bdsm.core.helpers.MessageService;
import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
@RequestMapping("/warehouse")
public class WarehouseController {
  private final WarehouseService warehouseService;
  private final MessageService messageService;

  @GetMapping
  public String warehouseStart(
      Model model,
      String message,
      @PageableDefault(
              size = 15,
              //              TODO: SORTING BY DTO VARIABLE
              sort = {"productId"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {
    model.addAttribute("warehousePages", warehouseService.getWarehouseList(pageable));
    model.addAttribute("message", messageService.getMessage(message));
    return "/warehouse/warehouse-all";
  }

  @PreAuthorize("hasAnyRole('DIETIST')")
  @GetMapping("/warehouse-record")
  public String newWarehouseRecord(Model model, String message) {
    model.addAttribute("warehouseDto", warehouseService.getEmptyWarehouseDto());
    model.addAttribute("allProductsList", warehouseService.getAllProductsNames());
    model.addAttribute("message", messageService.getMessage(message));
    return "warehouse/warehouse-record";
  }

  @PreAuthorize("hasAnyRole('DIETIST')")
  @PostMapping("/warehouse-record")
  public String saveWarehouseRecord(@Valid WarehouseDto warehouseDto, BindingResult errors) {
    if (errors.hasErrors()) {
      return "warehouse/warehouse-record";
    }
    warehouseService.addWarehouseRecord(warehouseDto);
    return "redirect:/warehouse/warehouse-record?message=warehouse.create.success.message";
  }

  @PreAuthorize("hasAnyRole('DIETIST')")
  @GetMapping("/{uuid}/update")
  public String updateWarehouse(Model model, @PathVariable("uuid") UUID uuid) {
    model.addAttribute("warehouseDto", warehouseService.getWarehouseDtoRecordByUUID(uuid));
    return "/warehouse/warehouse-record";
  }

  @PreAuthorize("hasAnyRole('DIETIST')")
  @PostMapping("/{uuid}/update")
  public String updateWarehouse(WarehouseDto warehouseDto) {
    warehouseService.updateWarehouse(warehouseDto);
    return "redirect:/warehouse?message=warehouse.update.successes";
  }

  @PreAuthorize("hasAnyRole('DIETIST')")
  @GetMapping("/{uuid}/delete")
  public String deleteWarehouse(@PathVariable("uuid") UUID uuid) {
    warehouseService.deleteWarehouseRecord(uuid);
    return "redirect:/warehouse?message=warehouse.delete.successes";
  }

  @PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
  @GetMapping("/{uuid}/writeOff")
  public String writeOff(Model model, @PathVariable("uuid") UUID uuid) {
    model.addAttribute("warehouseDto", warehouseService.getWarehouseDtoRecordByUUID(uuid));
    return "/warehouse/write-off";
  }

  @PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
  @PostMapping("/{uuid}/writeOff")
  public String writeOff(
      @Valid WarehouseDto warehouseDto, BindingResult errors, @PathVariable("uuid") UUID uuid) {
    if (errors.hasErrors()) {
      return "/warehouse/write-off";
    }
    warehouseService.writeOff(warehouseDto.getWriteOff(), uuid);
    return "redirect:/warehouse?message=warehouse.write.off.successes";
  }
}
