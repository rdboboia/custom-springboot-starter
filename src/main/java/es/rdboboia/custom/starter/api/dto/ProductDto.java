package es.rdboboia.custom.starter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long id;
	private String name;
	private ProductTypeDto type;
}
