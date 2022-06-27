package eu.kiminiuslt.bdsm.api.commons.apiDocumentation;

import eu.kiminiuslt.bdsm.core.menu.model.dto.DayShortageDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDayDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/foodMenu")
@Api(tags = "Food menu Controller")
public interface FoodMenuApiDocumentation {
  String AUTHENTICATION = "Authentication required";
  String AUTHORIZATION = "User don't have permission use this function";

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Get all month menu",
      notes = "Returns MenuDto object that contains list of MenuDayDto",
      httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered menu"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  MenuDto getMenu();

  @GetMapping("/update/{id}")
  @ApiOperation(value = "Get one day menu", notes = "Returns MenuDayDto object", httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered menu day"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  MenuDayDto getDayMenu(@PathVariable("id") Integer id);

  @PostMapping("/add/{id}/{uuid}")
  @ApiOperation(
      value = "Add one recipe to day",
      notes = "Returns same menu day, but with added recipe",
      httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered day menu"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  MenuDayDto addRecipeIntoDay(
      @PathVariable("id") Integer id, @PathVariable("uuid") UUID recipeUUID);

  @PostMapping("/remove/{id}/{uuid}")
  @ApiOperation(
      value = "remove one recipe from day",
      notes = "Returns same menu day, but with removed recipe",
      httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered day menu"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  MenuDayDto removeRecipeFromDay(
      @PathVariable("id") Integer id, @PathVariable("uuid") UUID recipeUUID);

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
          value = "Returns list of days with calculated shortages",
          notes = "One PeopleCountDto is for one menu day. " +
                  "If day has no calculated shortages, productsShortageDtoList will be empty",
          httpMethod = "POST")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Successfully delivered List"),
                  @ApiResponse(code = 401, message = AUTHENTICATION),
                  @ApiResponse(code = 403, message = AUTHORIZATION)
          })
  List<DayShortageDto> calculateShortage(@RequestBody List<PeopleCountDto> peopleCountDtoList);
}
