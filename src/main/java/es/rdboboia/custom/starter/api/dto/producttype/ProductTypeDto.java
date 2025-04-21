package es.rdboboia.custom.starter.api.dto.producttype;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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

  @NotNull
  @Schema(description = "Product type ID", example = "1")
  private Long id;

  @Null
  @Schema(description = "Product type name", example = "Some product type name")
  private String name;
}
