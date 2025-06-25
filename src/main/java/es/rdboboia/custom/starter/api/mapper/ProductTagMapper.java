package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.producttag.ProductTagDto;
import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import java.util.List;
import org.mapstruct.Mapper;

/** {@link ProductTag} mapper. */
@Mapper
public interface ProductTagMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  ProductTag toEntity(ProductTagDto dto);

  /* ****** */
  /* TO DTO */
  /* ****** */

  ProductTagDto toDto(ProductTag entity);

  List<ProductTagDto> toDto(List<ProductTag> entityList);
}
