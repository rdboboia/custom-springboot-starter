package es.rdboboia.custom.starter.api.dto;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** {@link ProductType} DTO. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypeDto {

  private Long id;
  private String name;
}
