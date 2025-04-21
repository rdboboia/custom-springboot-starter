package es.rdboboia.custom.starter.api.dto.product;

import es.rdboboia.custom.starter.persistence.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** {@link Product} DTO. */
@Schema(description = "Product object")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductDto extends ProductWithoutIdDto {

  @Schema(description = "Product ID", example = "1")
  private Long id;
}
