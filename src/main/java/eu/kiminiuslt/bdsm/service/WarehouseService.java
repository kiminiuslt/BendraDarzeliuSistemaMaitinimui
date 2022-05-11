package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseService {
  private final WarehouseRepository warehouseRepository;
  private final WarehouseMapper warehouseMapper;

  public void addWarehouseRecord(WarehouseDto warehouseDto) {
    warehouseRepository.save(warehouseMapper.warehouseDtoToWarehouse(warehouseDto));
  }

  public Page<WarehouseDto> getWarehouseList(Pageable pageable) {
    return warehouseRepository.findAll(pageable).map(warehouseMapper::warehouseToWarehouseDto);
  }
}
