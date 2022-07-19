package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.history.HistoryService;
import eu.kiminiuslt.bdsm.jpa.entity.History;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static eu.kiminiuslt.bdsm.commons.Constants.AUTHENTICATION;
import static eu.kiminiuslt.bdsm.commons.Constants.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
@PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
@Api(tags = "History Controller")
public class HistoryApiController {

  private final HistoryService historyService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered page"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  @ApiOperation(value = "Get all history records", httpMethod = "GET")
  public Page<History> getAllListPaginated(
      @ApiParam(
              name = "page",
              value = "Number of page",
              type = "int",
              example = "1",
              required = true)
          @RequestParam("page")
          int page,
      @ApiParam(
              name = "size",
              value = "Content size in page",
              type = "int",
              example = "5",
              required = true)
          @RequestParam("size")
          int size) {
    return historyService.getHistoryPage(PageRequest.of(page, size));
  }
}
