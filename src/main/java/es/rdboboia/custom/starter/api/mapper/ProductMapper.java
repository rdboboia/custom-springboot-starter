package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.product.ProductDto;
import es.rdboboia.custom.starter.api.dto.product.ProductPostDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** {@link Product} mapper. */
@Mapper(uses = {ProductTypeMapper.class})
public interface ProductMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  Product toEntity(ProductDto productDto);

  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductPostDto dto);

  /* ****** */
  /* TO DTO */
  /* ****** */

  ProductDto toDto(Product product);

  List<ProductDto> toDto(List<Product> products);
}
