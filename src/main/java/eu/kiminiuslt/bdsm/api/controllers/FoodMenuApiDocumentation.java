package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.menu.model.dto.MenuDto;
import eu.kiminiuslt.bdsm.core.menu.model.dto.PeopleCountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/foodMenu")
@Api(tags = "Food menu Controller")
public interface FoodMenuApiDocumentation {
  String AUTHENTICATION = "Authentication required";
  String AUTHORIZATION = "User don't have permission use this function";

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get all month menu",
          notes = "Returns MenuDto object that contains list of MenuDayDto",
          httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered menu"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  MenuDto getMenu();

  @PostMapping("/{id}")
  @ApiOperation(value = "Add one recipe to day", httpMethod = "POST")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Successfully delivered menu"),
                  @ApiResponse(code = 401, message = AUTHENTICATION),
                  @ApiResponse(code = 403, message = AUTHORIZATION)
          })
  ResponseEntity<Void> addRecipeIntoDay(@PathVariable("id") Integer id, String recipeUUID);
}
