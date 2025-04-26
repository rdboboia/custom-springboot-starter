package es.rdboboia.custom.starter.api.dto.product;

import es.rdboboia.custom.starter.api.dto.producttype.ProductTypePostDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link Product} DTO. */
@Schema(description = "Product object for save / update operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPostDto {

  static final String ID_DESCRIPTION = "Product ID";
  static final String ID_EXAMPLE = "1";

  static final String NAME_DESCRIPTION = "Product name";
  static final String NAME_EXAMPLE = "Some product name";

  @NotBlank
  @Schema(description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
  private String name;

  @Valid @NotNull private ProductTypePostDto type;
}
