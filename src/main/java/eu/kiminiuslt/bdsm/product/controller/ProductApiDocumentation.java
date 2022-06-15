package eu.kiminiuslt.bdsm.product.controller;

import eu.kiminiuslt.bdsm.product.model.dto.ProductDto;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/products")
@Api(tags = "Product Controller")
public interface ProductApiDocumentation {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Get products page",
      notes = "Chunk of all products list implemented by pagination",
      httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully delivered page"),
        @ApiResponse(code = 401, message = "Authentication required to get data"),
        @ApiResponse(code = 403, message = "User don't have permission to access this data")
      })
  Page<ProductDto> getProductsPaginated(
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

  @PostMapping
  @ApiOperation(value = "Create product", httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully saved new product"),
        @ApiResponse(code = 401, message = "Authentication required"),
        @ApiResponse(code = 403, message = "User don't have permission create product")
      })
  ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDto productDto);

  @PutMapping
  @ApiOperation(value = "Update product", httpMethod = "PUT", notes = "Updates only one product")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Product updated successfully"),
        @ApiResponse(code = 401, message = "Authentication required"),
        @ApiResponse(code = 403, message = "User don't have permission update product")
      })
  ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductDto productDto);

  @DeleteMapping("/{uuid}")
  @ApiOperation(value = "Delete product", httpMethod = "DELETE")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Product deleted successfully"),
        @ApiResponse(code = 401, message = "Authentication required"),
        @ApiResponse(code = 403, message = "User don't have permission delete product")
      })
  ResponseEntity<Void> deleteProduct(@PathVariable("uuid") UUID uuid);
}
