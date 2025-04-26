package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeParentPostDto;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** {@link ProductType} mapper. */
@Mapper
public interface ProductTypeMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  ProductType toEntity(ProductTypeDto dto);

  @Mapping(target = "name", ignore = true)
  ProductType toEntity(ProductTypeParentPostDto dto);

  /* ****** */
  /* TO DTO */
  /* ****** */

}
