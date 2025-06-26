package es.rdboboia.custom.starter.api.dto.product;

import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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

  static final String ID_DESCRIPTION = "Product ID";
  static final String ID_EXAMPLE = "1";

  static final String NAME_DESCRIPTION = "Product name";
  static final String NAME_EXAMPLE = "Some product name";

  @Schema(description = ID_DESCRIPTION, example = ID_EXAMPLE)
  private Long id;

  @Schema(description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
  private String name;

  private ProductTypeDto type;
  private List<String> tags;
}
