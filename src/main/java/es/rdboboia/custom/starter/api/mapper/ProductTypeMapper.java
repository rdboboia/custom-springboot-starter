package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeParentPostDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypePostDto;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** {@link ProductType} mapper. */
@Mapper
public interface ProductTypeMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  ProductType toEntity(ProductTypeDto dto);

  @Mapping(target = "id", ignore = true)
  ProductType toEntity(ProductTypePostDto dto);

  @Mapping(target = "name", ignore = true)
  ProductType toEntity(ProductTypeParentPostDto dto);

  /* ****** */
  /* TO DTO */
  /* ****** */

  ProductTypeDto toDto(ProductType entity);

  List<ProductTypeDto> toDto(List<ProductType> entityList);
}
