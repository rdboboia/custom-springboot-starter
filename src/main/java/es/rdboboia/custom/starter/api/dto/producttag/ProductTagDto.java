package es.rdboboia.custom.starter.api.dto.producttag;

import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link ProductTag} DTO. */
@Schema(description = "Product tag object")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTagDto {

  static final String ID_DESCRIPTION = "Product tag ID";
  static final String ID_EXAMPLE = "1";

  static final String NAME_DESCRIPTION = "Product tag name";
  static final String NAME_EXAMPLE = "Some product tag name";

  @Schema(description = ID_DESCRIPTION, example = ID_EXAMPLE)
  private Long id;

  @Schema(description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
  private String name;
}
