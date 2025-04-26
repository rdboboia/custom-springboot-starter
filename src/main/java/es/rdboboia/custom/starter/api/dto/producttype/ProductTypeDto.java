package es.rdboboia.custom.starter.api.dto.producttype;

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

  static final String ID_DESCRIPTION = "Product type ID";
  static final String ID_EXAMPLE = "1";

  static final String NAME_DESCRIPTION = "Product type name";
  static final String NAME_EXAMPLE = "Some product type name";

  @Schema(description = ID_DESCRIPTION, example = ID_EXAMPLE)
  private Long id;

  @Schema(description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
  private String name;
}
