package es.rdboboia.custom.starter.api.controller;

import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypePostDto;
import es.rdboboia.custom.starter.api.error.dto.ValidationErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/** Product type API. */
@Tag(name = "Product type", description = "Product type CRUD API")
@RequestMapping(ProductTypeController.BASE_URL)
public interface ProductTypeController {

  public static final String BASE_URL = "/product/type";
  public static final String ID_URL_VARIABLE = "/{id}";

  @Operation(summary = "Get product type list", description = "Get the list of all product types")
  @ApiResponse(responseCode = "200", description = "List of product types")
  @GetMapping
  List<ProductTypeDto> getAll();

  @Operation(summary = "Save product type", description = "Save a new product type")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Product type saved"),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = {
              @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class))
            })
      })
  @PostMapping
  ProductTypeDto save(@Valid @RequestBody ProductTypePostDto productTypeDto);

  @Operation(summary = "Update product type", description = "Update a product type non null fields")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Product type updated"),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = {
              @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class))
            })
      })
  @PatchMapping(value = ID_URL_VARIABLE)
  ProductTypeDto update(
      @PathVariable Long id, @Valid @RequestBody ProductTypePostDto productTypeDto);

  @Operation(summary = "Delete product type", description = "Delete a product type by ID")
  @ApiResponse(responseCode = "200", description = "Product type deleted")
  @DeleteMapping(value = ID_URL_VARIABLE)
  void delete(@PathVariable Long id);
}
