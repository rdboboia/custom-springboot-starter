package es.rdboboia.custom.starter.api.dto.product;

import es.rdboboia.custom.starter.api.dto.producttag.ProductTagDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeParentPostDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link Product} DTO. */
@Schema(description = "Product object for product PATCH operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPatchDto {

  @Schema(description = ProductDto.NAME_DESCRIPTION, example = ProductDto.NAME_EXAMPLE)
  private String name;

  @Valid private ProductTypeParentPostDto type;
  @Valid private List<ProductTagDto> tags;
}
