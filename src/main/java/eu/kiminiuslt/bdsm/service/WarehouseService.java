package eu.kiminiuslt.bdsm.service;

import eu.kiminiuslt.bdsm.mapper.WarehouseMapper;
import eu.kiminiuslt.bdsm.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
  private final WarehouseRepository warehouseRepository;
  private final WarehouseMapper warehouseMapper;
  private final ProductService productService;

  public void addWarehouseRecord(WarehouseDto warehouseDto) {
    warehouseRepository.save(warehouseMapper.warehouseDtoToWarehouse(warehouseDto));
  }

  public Page<WarehouseDto> getWarehouseList(Pageable pageable) {
    return warehouseRepository.findAll(pageable).map(warehouseMapper::warehouseToWarehouseDto);
  }

  public List<ProductsNamesDto> getAllProductsNames() {
    return productService.getProductsListNamesDto();
  }
}
