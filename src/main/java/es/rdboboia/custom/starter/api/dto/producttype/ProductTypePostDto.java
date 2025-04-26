package es.rdboboia.custom.starter.api.dto.producttype;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link ProductType} DTO. */
@Schema(description = "Product type object for product type POST / PATCH operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypePostDto {

  @NotBlank
  @Schema(description = ProductTypeDto.NAME_DESCRIPTION, example = ProductTypeDto.NAME_EXAMPLE)
  private String name;
}
