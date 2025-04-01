package es.rdboboia.custom.starter.api.controller;

import es.rdboboia.custom.starter.api.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of Product",
            content =
                @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal service error.",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  List<ProductDto> getAllProducts();

  @Operation(summary = "Get product by id", description = "Get the product by id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Product",
            content = @Content(schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Product not found",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal service error.",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @GetMapping(value = ID_URL_VARIABLE)
  ProductDto getProductById(@PathVariable Long id);

  @Operation(summary = "Save product", description = "Save a new product")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Product",
            content = @Content(schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal service error.",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @PostMapping
  ProductDto saveProduct(@RequestBody ProductDto productDto);

  @Operation(summary = "Update product", description = "Update a product non null fields")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Product",
            content = @Content(schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal service error.",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @PatchMapping(value = ID_URL_VARIABLE)
  ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto);

  @Operation(summary = "Delete product", description = "Delete a product by id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Product deleted"),
        @ApiResponse(
            responseCode = "500",
            description = "Internal service error.",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @DeleteMapping(value = ID_URL_VARIABLE)
  void deleteProduct(@PathVariable Long id);
}
