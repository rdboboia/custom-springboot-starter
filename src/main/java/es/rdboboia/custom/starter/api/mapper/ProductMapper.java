package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.product.ProductDto;
import es.rdboboia.custom.starter.api.dto.product.ProductWithoutIdDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** {@link Product} and {@link ProductType} mapper. */
@Mapper
public interface ProductMapper {

  /* ********* */
  /* TO ENTITY */
  /* ********* */

  Product toEntity(ProductDto productDto);

  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductWithoutIdDto productPostDto);

  /* ****** */
  /* TO DTO */
  /* ****** */

  ProductDto toDto(Product product);

  List<ProductDto> toDto(List<Product> products);
}
