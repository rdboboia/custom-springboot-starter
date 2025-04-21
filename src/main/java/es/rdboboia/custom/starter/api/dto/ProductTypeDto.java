package es.rdboboia.custom.starter.api.dto;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link ProductType} DTO. */
@Schema(description = "Product type object")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypeDto {

  @Schema(description = "Product type ID", example = "1")
  private Long id;

  @Schema(description = "Product type name", example = "Some product type name")
  private String name;
}
