package es.rdboboia.custom.starter.api.mapper;

import es.rdboboia.custom.starter.api.dto.ProductDto;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import java.util.List;
import org.mapstruct.Mapper;

/** {@link Product} and {@link ProductType} mapper. */
@Mapper
public interface ProductMapper {

  ProductDto toDto(Product product);

  List<ProductDto> toDto(List<Product> products);

  Product toEntity(ProductDto productDto);
}
