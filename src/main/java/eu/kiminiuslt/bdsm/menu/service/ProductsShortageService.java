package eu.kiminiuslt.bdsm.menu.service;

import eu.kiminiuslt.bdsm.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.menu.model.dto.PeopleCountDto;
import eu.kiminiuslt.bdsm.menu.model.dto.ProductShortageDto;
import eu.kiminiuslt.bdsm.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;
import eu.kiminiuslt.bdsm.warehouse.service.WarehouseService;
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
            createProductShortageDto(allRequiredDayProducts, allRequiredDayProducts.get(i)));
      }
    }
    double totalPeople = getTotalPeople(peopleCountDto);
    return getFilteredListByWarehouse(summedRequiredGramsList, totalPeople);
  }

  private List<ProductShortageDto> getFilteredListByWarehouse(
      List<ProductShortageDto> summedRequiredGramsList, double totalPeople) {
    List<ProductShortageDto> result = new ArrayList<>();

    for (int i = 0; i < summedRequiredGramsList.size(); i++) {
      Warehouse warehouse =
          warehouseService.getWarehouseDtoById(summedRequiredGramsList.get(i).getProductId());
      if (warehouse == null) {
        result.add(setShortageMax(summedRequiredGramsList.get(i), totalPeople));
      } else {
        if (warehouse.getAmount()
            < (summedRequiredGramsList.get(i).getRequiredGrams() / 1000) * totalPeople) {
          result.add(setShortage(warehouse, summedRequiredGramsList.get(i), totalPeople));
        }
      }
    }
    return result;
  }

  private ProductShortageDto setShortage(
      Warehouse warehouse, ProductShortageDto productShortageDto, double totalPeople) {
    productShortageDto.setOwnedKg(warehouse.getAmount());
    productShortageDto.setShortageKg(
        countShortage(productShortageDto.getRequiredGrams(), totalPeople) - warehouse.getAmount());
    return productShortageDto;
  }

  private ProductShortageDto setShortageMax(
      ProductShortageDto productShortageDto, double totalPeople) {
    productShortageDto.setShortageKg(
        countShortage(productShortageDto.getRequiredGrams(), totalPeople));
    productShortageDto.setOwnedKg(0.0);
    return productShortageDto;
  }

  private boolean containsInList(
      List<ProductShortageDto> productShortageDto, ProductAndQuantityDto productAndQuantityDto) {
    return productShortageDto.stream()
        .anyMatch(o -> productAndQuantityDto.getProductUUID().equals(o.getUuid()));
  }

  private ProductShortageDto createProductShortageDto(
      List<ProductAndQuantityDto> listOfProductAndQuantity, ProductAndQuantityDto e) {
    return ProductShortageDto.builder()
        .name(e.getProduct().getName())
        .uuid(e.getProductUUID())
        .productId(e.getProduct().getId())
        .requiredGrams(
            listOfProductAndQuantity.stream()
                .filter(o -> o.getProductUUID().equals(e.getProductUUID()))
                .reduce(0.0, (subtotal, element) -> subtotal + element.getQuantity(), Double::sum))
        .build();
  }

  private double getTotalPeople(PeopleCountDto peopleCountDto) {
    double result = peopleCountDto.getLittleOnes();
    result = result + peopleCountDto.getOlderKids();
    result = result + peopleCountDto.getWorkers();
    return result;
  }

  private Double countShortage(Double requiredGrams, double totalPeople) {
    return round(requiredGrams / 1000 * totalPeople);
  }

  private double round(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
