package eu.kiminiuslt.bdsm.api.documentation;

import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static eu.kiminiuslt.bdsm.commons.Constants.AUTHENTICATION;
import static eu.kiminiuslt.bdsm.commons.Constants.AUTHORIZATION;

public interface CrudApiDocumentation<T> {

  @PostMapping
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully saved"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  ResponseEntity<Void> create(@Valid @RequestBody T object);

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered page"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  Page<T> readPaginated(
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
          int size);

  @PutMapping
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Update operation successful"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  ResponseEntity<Void> update(@Valid @RequestBody T object);

  @DeleteMapping("/{uuid}")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Delete operation successful"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  ResponseEntity<Void> delete(@PathVariable("uuid") UUID uuid);
}
