package eu.kiminiuslt.bdsm.product.controller;

import eu.kiminiuslt.bdsm.product.model.dto.ProductApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/products")
@Api(tags = "Product Controller")
public interface ProductApiDocumentation {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all products", notes = "Get all products from database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully delivered list"),
                    @ApiResponse(code = 401, message = "Authentication required to get data"),
                    @ApiResponse(code = 403, message = "User don't have permission to access this data")
            })
    ProductApiResponseDto getProducts();
}
