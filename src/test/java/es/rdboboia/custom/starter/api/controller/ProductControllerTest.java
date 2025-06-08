package es.rdboboia.custom.starter.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.rdboboia.custom.starter.api.dto.product.ProductDto;
import es.rdboboia.custom.starter.api.dto.product.ProductPatchDto;
import es.rdboboia.custom.starter.api.dto.product.ProductPostDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeParentPostDto;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class mainly focused on the API layer since the implementation itself does not have that
 * much happening.
 */
@WebMvcTest(ProductController.class)
@ExtendWith({VerifyNoMoreInteractionsExtension.class})
class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductService productService;
  @MockitoBean private ProductMapper productMapper;

  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .registerModule(new PageJacksonModule())
          .registerModule(new SortJacksonModule())
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  /**
   * Test for getAllProducts method.
   *
   * <p>Note here that we are checking that the filters are being mapped correctly before calling
   * the mapper. If the filters are not mapped correctly, the test will fail before reaching the
   * mapper.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void getAllProductsTest() throws Exception {
    // Init
    ProductTypeDto productTypeDto = ProductTypeDto.builder().id(1L).build();
    ProductDto filtersDto = ProductDto.builder().id(1L).type(productTypeDto).build();
    Product filters = new Product();

    Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
    Page<Product> products = Page.empty();
    Page<ProductDto> productDtos = Page.empty();

    // Arrange
    when(this.productMapper.toEntity(filtersDto)).thenReturn(filters);
    when(this.productService.getAllProducts(filters, pageable)).thenReturn(products);
    when(this.productMapper.toDto(products)).thenReturn(productDtos);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(get(ProductController.BASE_URL).param("id", "1").param("type.id", "1"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    Page<ProductDto> response =
        this.objectMapper.readValue(responseContentAsString, new TypeReference<>() {});

    // Assert
    assertEquals(productDtos.getContent(), response.getContent());
    assertEquals(productDtos.getNumber(), response.getNumber());
    assertEquals(productDtos.getSize(), response.getSize());
    assertEquals(productDtos.getTotalElements(), response.getTotalElements());

    // Verify
    verify(this.productMapper).toEntity(filtersDto);
    verify(this.productService).getAllProducts(filters, pageable);
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
    String responseContentAsString =
        this.mockMvc
            .perform(get(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id))
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
    ProductTypeParentPostDto productTypeDto = ProductTypeParentPostDto.builder().id(1L).build();
    ProductPostDto productPostDto =
        ProductPostDto.builder().name("Product").type(productTypeDto).build();
    ProductDto productDto = new ProductDto();

    Product product = new Product();

    // Arrange
    when(this.productMapper.toEntity(productPostDto)).thenReturn(product);
    when(this.productService.saveProduct(product)).thenReturn(product);
    when(this.productMapper.toDto(product)).thenReturn(productDto);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(
                post(ProductController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(productPostDto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    ProductDto response = this.objectMapper.readValue(responseContentAsString, ProductDto.class);

    // Assert
    assertEquals(productDto, response);

    // Verify
    verify(this.productMapper).toEntity(productPostDto);
    verify(this.productService).saveProduct(product);
    verify(this.productMapper).toDto(product);
  }

  @Test
  void updateProductTest() throws Exception {
    // Init
    Long id = 1L;
    ProductTypeParentPostDto productTypeDto = ProductTypeParentPostDto.builder().id(1L).build();
    ProductPatchDto productPatchDto =
        ProductPatchDto.builder().name("Product").type(productTypeDto).build();
    ProductDto productDto = new ProductDto();

    Product product = new Product();

    // Arrange
    when(this.productMapper.toEntity(productPatchDto)).thenReturn(product);
    when(this.productService.updateProduct(id, product)).thenReturn(product);
    when(this.productMapper.toDto(product)).thenReturn(productDto);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(
                patch(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(productPatchDto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    ProductDto response = this.objectMapper.readValue(responseContentAsString, ProductDto.class);

    // Assert
    assertEquals(productDto, response);

    // Verify
    verify(this.productMapper).toEntity(productPatchDto);
    verify(this.productService).updateProduct(id, product);
    verify(this.productMapper).toDto(product);
  }

  @Test
  void deleteProductTest() throws Exception {
    // Init
    Long id = 1L;

    // Act
    this.mockMvc
        .perform(delete(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id))
        .andExpect(status().isOk());

    // Verify
    verify(this.productService).deleteProduct(id);
  }
}
