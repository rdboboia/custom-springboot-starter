package es.rdboboia.custom.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import es.rdboboia.custom.starter.api.controller.ProductController;
import es.rdboboia.custom.starter.api.dto.ProductDto;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.service.ProductService;

@WebMvcTest(ProductController.class)
@ExtendWith({ VerifyNoMoreInteractionsExtension.class })
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProductService productService;

	@MockitoBean
	private ProductMapper productMapper;

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Test
	void getAllProductsTest() throws Exception {
		// Init
		List<Product> products = List.of();
		List<ProductDto> productDtos = List.of();

		// Arrange
		when(this.productService.getAllProducts()).thenReturn(products);
		when(this.productMapper.toDto(products)).thenReturn(productDtos);

		// Act
		String responseContentAsString = this.mockMvc.perform(get(ProductController.BASE_URL))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		List<ProductDto> response = this.objectMapper.readValue(responseContentAsString, new TypeReference<>() {
		});

		// Assert
		assertEquals(productDtos, response);

		// Verify
		verify(this.productService).getAllProducts();
		verify(this.productMapper).toDto(products);
	}

	@Test
	void getProductByIdTest() throws Exception {
		// Init
		Long id = 1L;
		Product product = new Product();
		ProductDto productDto = new ProductDto();

		// Arrange
		when(this.productService.getProductById(id)).thenReturn(product);
		when(this.productMapper.toDto(product)).thenReturn(productDto);

		// Act
		String responseContentAsString = this.mockMvc.perform(get(ProductController.BASE_URL + "/" + id))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		ProductDto response = this.objectMapper.readValue(responseContentAsString, ProductDto.class);

		// Assert
		assertEquals(productDto, response);

		// Verify
		verify(this.productService).getProductById(id);
		verify(this.productMapper).toDto(product);
	}

	@Test
	void saveProductTest() throws Exception {
		// Init
		ProductDto productDto = new ProductDto();
		Product product = new Product();

		// Arrange
		when(this.productMapper.toEntity(productDto)).thenReturn(product);
		when(this.productService.saveProduct(product)).thenReturn(product);
		when(this.productMapper.toDto(product)).thenReturn(productDto);

		// Act
		String responseContentAsString = this.mockMvc
				.perform(post(ProductController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(productDto)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		ProductDto response = this.objectMapper.readValue(responseContentAsString, ProductDto.class);

		// Assert
		assertEquals(productDto, response);

		// Verify
		verify(this.productMapper).toEntity(productDto);
		verify(this.productService).saveProduct(product);
		verify(this.productMapper).toDto(product);
	}

	@Test
	void updateProductTest() throws Exception {
		// Init
		Long id = 1L;
		ProductDto productDto = new ProductDto();
		Product product = new Product();

		// Arrange
		when(this.productMapper.toEntity(productDto)).thenReturn(product);
		when(this.productService.updateProduct(id, product)).thenReturn(product);
		when(this.productMapper.toDto(product)).thenReturn(productDto);

		// Act
		String responseContentAsString = this.mockMvc
				.perform(patch(ProductController.BASE_URL + "/" + id).contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(productDto)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		ProductDto response = this.objectMapper.readValue(responseContentAsString, ProductDto.class);

		// Assert
		assertEquals(productDto, response);

		// Verify
		verify(this.productMapper).toEntity(productDto);
		verify(this.productService).updateProduct(id, product);
		verify(this.productMapper).toDto(product);
	}

	@Test
	void deleteProductTest() throws Exception {
		// Init
		Long id = 1L;

		// Act
		this.mockMvc.perform(delete(ProductController.BASE_URL + "/" + id)).andExpect(status().isOk());

		// Verify
		verify(this.productService).deleteProduct(id);
	}

}
