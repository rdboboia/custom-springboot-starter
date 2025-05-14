package es.rdboboia.custom.starter.api.controller;

import es.rdboboia.custom.starter.api.dto.product.ProductDto;
import es.rdboboia.custom.starter.api.dto.product.ProductPatchDto;
import es.rdboboia.custom.starter.api.dto.product.ProductPostDto;
import es.rdboboia.custom.starter.api.error.dto.ValidationErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/** Product API. */
@Tag(name = "Product", description = "Product CRUD API")
@RequestMapping(ProductController.BASE_URL)
public interface ProductController {

  public static final String BASE_URL = "/product";
  public static final String ID_URL_VARIABLE = "/{id}";

  @Operation(summary = "Get product list", description = "Get the list of all products")
  @ApiResponse(responseCode = "200", description = "List of products")
  @GetMapping
  Page<ProductDto> getAll(
      @ModelAttribute ProductDto filters, @PageableDefault(sort = {"name"}) Pageable pageable);

  @Operation(summary = "Get product by id", description = "Get the product by ID")
  @ApiResponse(responseCode = "200", description = "Product object")
  @GetMapping(value = ID_URL_VARIABLE)
  ProductDto getById(@PathVariable Long id);

  @Operation(summary = "Save product", description = "Save a new product")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Product saved"),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = {
              @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class))
            })
      })
  @PostMapping
  ProductDto save(@Valid @RequestBody ProductPostDto productDto);

  @Operation(summary = "Update product", description = "Update a product non null fields")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Product updated"),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = {
              @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class))
            })
      })
  @PatchMapping(value = ID_URL_VARIABLE)
  ProductDto update(@PathVariable Long id, @Valid @RequestBody ProductPatchDto productDto);

  @Operation(summary = "Delete product", description = "Delete a product by ID")
  @ApiResponse(responseCode = "200", description = "Product deleted")
  @DeleteMapping(value = ID_URL_VARIABLE)
  void delete(@PathVariable Long id);
}
