package es.rdboboia.custom.starter.api.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.rdboboia.custom.starter.api.controller.ProductController;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.service.ProductService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
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

    // Arrange
    when(this.productService.getAllProducts()).thenThrow(new NoSuchElementException());

    // Act
    MockHttpServletResponse response =
        this.mockMvc.perform(get(ProductController.BASE_URL)).andReturn().getResponse();

    // Assert
    assertEquals(404, response.getStatus());

    // Verify
    verify(this.productService).getAllProducts();
  }
}
