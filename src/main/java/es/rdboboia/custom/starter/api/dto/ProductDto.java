package es.rdboboia.custom.starter.api.dto;

import es.rdboboia.custom.starter.persistence.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link Product} DTO. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

  private Long id;
  private String name;
  private ProductTypeDto type;
}
