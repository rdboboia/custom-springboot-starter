package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** {@link ProductTag} mapper. */
@Mapper
public interface ProductTagMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "dto")
  ProductTag toEntity(String dto);

  /* ****** */
  /* TO DTO */
  /* ****** */

  default String toDto(ProductTag entity) {
    return entity != null ? entity.getName() : null;
  }

  List<String> toDto(List<ProductTag> entityList);
}
