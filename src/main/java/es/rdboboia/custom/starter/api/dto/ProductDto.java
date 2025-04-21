package es.rdboboia.custom.starter.api.dto;

import es.rdboboia.custom.starter.persistence.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link Product} DTO. */
@Schema(description = "Product object")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

  @Schema(description = "Product ID", example = "1")
  private Long id;

  @Schema(description = "Product name", example = "Some product name")
  private String name;

  @Schema(description = "Product type")
  private ProductTypeDto type;
}
