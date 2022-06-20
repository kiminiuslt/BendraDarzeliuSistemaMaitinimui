package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.ProductShortageDto;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.jpa.entity.Warehouse;
import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductsShortageService {
  private final WarehouseService warehouseService;

  public List<ProductShortageDto> getProductsShortageList(
      MenuDayDto menuDayDto, PeopleCountDto peopleCountDto) {

    List<ProductAndQuantityDto> allRequiredDayProducts = new ArrayList<>();
    List<ProductShortageDto> summedRequiredGramsList = new ArrayList<>();
    menuDayDto.getDayRecipesDto().forEach(e -> allRequiredDayProducts.addAll(e.getProductsList()));

    for (int i = 0; i < allRequiredDayProducts.size(); i++) {
      if (!containsInList(summedRequiredGramsList, allRequiredDayProducts.get(i))) {
        summedRequiredGramsList.add(
            createProductShortageDto(
                peopleCountDto, allRequiredDayProducts, allRequiredDayProducts.get(i)));
      }
    }
    return getFilteredListByWarehouse(summedRequiredGramsList);
  }

  private List<ProductShortageDto> getFilteredListByWarehouse(
      List<ProductShortageDto> summedRequiredGramsList) {
    List<ProductShortageDto> result = new ArrayList<>();

    for (int i = 0; i < summedRequiredGramsList.size(); i++) {
      Warehouse warehouse =
          warehouseService.getWarehouseById(summedRequiredGramsList.get(i).getProductId());
      if (warehouse == null) {
        result.add(setShortageMax(summedRequiredGramsList.get(i)));
      } else {
        if (warehouse.getAmount() < (summedRequiredGramsList.get(i).getRequiredGrams() / 1000)) {
          result.add(setShortage(warehouse, summedRequiredGramsList.get(i)));
        }
      }
    }
    return result;
  }

  private ProductShortageDto setShortage(
      Warehouse warehouse, ProductShortageDto productShortageDto) {
    productShortageDto.setOwnedKg(warehouse.getAmount());
    productShortageDto.setShortageKg(
        countShortage(productShortageDto.getRequiredGrams()) - warehouse.getAmount());
    return productShortageDto;
  }

  private ProductShortageDto setShortageMax(ProductShortageDto productShortageDto) {
    productShortageDto.setShortageKg(countShortage(productShortageDto.getRequiredGrams()));
    productShortageDto.setOwnedKg(0.0);
    return productShortageDto;
  }

  private boolean containsInList(
      List<ProductShortageDto> productShortageDto, ProductAndQuantityDto productAndQuantityDto) {
    return productShortageDto.stream()
        .anyMatch(o -> productAndQuantityDto.getProductUUID().equals(o.getUuid()));
  }

  private ProductShortageDto createProductShortageDto(
      PeopleCountDto peopleCountDto,
      List<ProductAndQuantityDto> listOfProductAndQuantity,
      ProductAndQuantityDto productAndQuantityDto) {

    Integer smallPortions = peopleCountDto.getLittleOnes();
    Integer normalPortions = peopleCountDto.getOlderKids() + peopleCountDto.getWorkers();

    return ProductShortageDto.builder()
        .name(productAndQuantityDto.getProduct().getName())
        .uuid(productAndQuantityDto.getProductUUID())
        .productId(productAndQuantityDto.getProduct().getId())
        .requiredGrams(
            listOfProductAndQuantity.stream()
                .filter(o -> o.getProductUUID().equals(productAndQuantityDto.getProductUUID()))
                .reduce(
                    0.0,
                    (subtotal, e) ->
                        subtotal
                            + e.getQuantityGross() * normalPortions
                            + e.getQuantityGrossLittleOnes() * smallPortions,
                    Double::sum))
        .build();
  }

  private Double countShortage(Double requiredGrams) {
    return round(requiredGrams / 1000);
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
