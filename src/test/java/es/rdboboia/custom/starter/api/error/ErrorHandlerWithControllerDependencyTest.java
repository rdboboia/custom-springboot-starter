package es.rdboboia.custom.starter.api.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@ExtendWith({VerifyNoMoreInteractionsExtension.class})
class ErrorHandlerWithControllerDependencyTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductService productService;

  @MockitoBean private ProductMapper productMapper;

  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  void getAllProductsTest() throws Exception {
    // Init
    List<Product> products = List.of();
    List<ProductDto> productDtos = List.of();

    // Arrange
    when(this.productService.getAllProducts()).thenThrow(new NoSuchElementException());
    when(this.productMapper.toDto(products)).thenReturn(productDtos);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(get(ProductController.BASE_URL))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    List<ProductDto> response =
        this.objectMapper.readValue(responseContentAsString, new TypeReference<>() {});

    // Assert
    assertEquals(productDtos, response);

    // Verify
    verify(this.productService).getAllProducts();
    verify(this.productMapper).toDto(products);
  }
}
