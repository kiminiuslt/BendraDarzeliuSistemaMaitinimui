package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.api.commons.apiDocumentation.CrudApiDocumentation;
import eu.kiminiuslt.bdsm.core.history.HistoryService;
import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
@PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
@Api(tags = "Warehouse Controller")
public class WarehouseApiController implements CrudApiDocumentation<WarehouseDto> {

  private final WarehouseService warehouseService;
  private final HistoryService historyService;

  @Override
  @PreAuthorize("hasAnyRole('DIETIST')")
  @ApiOperation(value = "Create warehouse record", httpMethod = "POST")
  public ResponseEntity<Void> create(WarehouseDto object) {
    warehouseService.addWarehouseRecord(object);
    historyService.savedWarehouseRecord(object.getProductName(),object.getAmount());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  @PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
  @ApiOperation(
      value = "Get warehouse records page",
      notes = "Chunk of all warehouse records list implemented by pagination",
      httpMethod = "GET")
  public Page<WarehouseDto> readPaginated(int page, int size) {
    return warehouseService.getWarehouseList(PageRequest.of(page, size));
  }

  @Override
  @PreAuthorize("hasAnyRole('DIETIST')")
  @ApiOperation(
      value = "Update warehouse record",
      httpMethod = "PUT",
      notes = "Updates only one warehouse record")
  public ResponseEntity<Void> update(WarehouseDto object) {
    warehouseService.updateWarehouse(object);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  @PreAuthorize("hasAnyRole('DIETIST')")
  @ApiOperation(value = "Delete warehouse record", httpMethod = "DELETE")
  public ResponseEntity<Void> delete(UUID uuid) {
    warehouseService.deleteWarehouseRecord(uuid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/writeOff/{uuid}")
  @PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
  @ApiOperation(value = "Write off warehouse", httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Write off operation successful"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  public ResponseEntity<Void> writeOff(
      @Valid WarehouseDto warehouseDto, @PathVariable("uuid") UUID uuid) {
    warehouseService.writeOff(warehouseDto.getWriteOff(), uuid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
