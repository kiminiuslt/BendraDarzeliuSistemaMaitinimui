package eu.kiminiuslt.bdsm.core.menu.service;

import eu.kiminiuslt.bdsm.core.menu.model.dto.DayShortageDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.ProductShortageDto;
import eu.kiminiuslt.bdsm.core.recipe.mapper.ProductAndQuantityMapper;
import eu.kiminiuslt.bdsm.core.recipe.model.dto.ProductAndQuantityDto;
import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import eu.kiminiuslt.bdsm.jpa.entity.Warehouse;
import eu.kiminiuslt.bdsm.jpa.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductShortageProvider {
  private final RecipeRepository recipeRepository;
  private final ProductAndQuantityMapper productAndQuantityMapper;
  private final DaySequenceEstimationService daySequenceEstimationService;
  private final WarehouseService warehouseService;
  private final ProductShortageHelper productShortageHelper;

  public List<ProductShortageDto> getDayShortage(
      MenuDayDto menuDayDto,
      List<DayShortageDto> alreadyCalculatedDays,
      PeopleCountDto peopleCountDto) {

    List<ProductAndQuantityDto> allRequiredDayProducts = new ArrayList<>();
    List<ProductShortageDto> summedRequiredGramsList = new ArrayList<>();

    menuDayDto.getDayRecipesNamesDto().stream()
        .map(o -> recipeRepository.findByUuid(o.getUuid()))
        .forEach(
            o ->
                allRequiredDayProducts.addAll(
                    productAndQuantityMapper.entityToDto(o.getProductsList())));

    for (int i = 0; i < allRequiredDayProducts.size(); i++) {
      if (!productShortageHelper.containsInList(
          summedRequiredGramsList, allRequiredDayProducts.get(i))) {
        summedRequiredGramsList.add(
            createProductShortageDto(
                peopleCountDto,
                allRequiredDayProducts,
                allRequiredDayProducts.get(i),
                alreadyCalculatedDays));
      }
    }
    return getListByWarehouse(summedRequiredGramsList);
  }

  private ProductShortageDto createProductShortageDto(
      PeopleCountDto peopleCountDto,
      List<ProductAndQuantityDto> listOfProductAndQuantity,
      ProductAndQuantityDto productAndQuantityDto,
      List<DayShortageDto> alreadyCalculatedDays) {

    Integer smallPortions = peopleCountDto.getLittleOnes();
    Integer normalPortions = peopleCountDto.getOlderKids() + peopleCountDto.getWorkers();
    ProductShortageDto result =
        ProductShortageDto.builder()
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
            .otherDaysRequiredGrams(0.0)
            .build();
    result.setOtherDaysRequiredGrams(
        daySequenceEstimationService.getOtherDaysRequiredGrams(result, alreadyCalculatedDays));
    return result;
  }

  private List<ProductShortageDto> getListByWarehouse(
      List<ProductShortageDto> summedRequiredGramsList) {
    List<ProductShortageDto> result = new ArrayList<>();

    for (ProductShortageDto productShortageDto : summedRequiredGramsList) {
      Warehouse warehouse = warehouseService.getWarehouseById(productShortageDto.getProductId());
      double allGrams =
          productShortageDto.getRequiredGrams() + productShortageDto.getOtherDaysRequiredGrams();
      if (warehouse == null) {
        result.add(productShortageHelper.setShortageMax(productShortageDto, allGrams));
      } else {

        result.add(productShortageHelper.setShortage(warehouse.getAmount(), productShortageDto));
      }
    }
    return result;
  }
}
