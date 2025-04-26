package es.rdboboia.custom.starter.api.dto.producttype;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link ProductType} DTO. */
@Schema(description = "Product type object for parent object POST / PATCH operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypeParentPostDto {

  @NotNull
  @Schema(description = ProductTypeDto.ID_DESCRIPTION, example = ProductTypeDto.ID_EXAMPLE)
  private Long id;
}
