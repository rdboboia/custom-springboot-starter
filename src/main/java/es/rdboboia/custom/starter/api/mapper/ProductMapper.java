package es.rdboboia.custom.starter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.rdboboia.custom.starter.api.dto.ProductDto;
import es.rdboboia.custom.starter.persistence.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductDto toDto(Product product);

	List<ProductDto> toDto(List<Product> products);

	Product toEntity(ProductDto productDto);

}
