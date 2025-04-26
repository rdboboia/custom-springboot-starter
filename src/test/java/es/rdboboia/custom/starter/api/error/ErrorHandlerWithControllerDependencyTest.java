package es.rdboboia.custom.starter.api.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.rdboboia.custom.starter.api.controller.ProductController;
import es.rdboboia.custom.starter.api.dto.product.ProductPostDto;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.service.ProductService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
  void handleMethodArgumentNotValidException() throws Exception {
    // Init
    ProductPostDto productDto = new ProductPostDto();

    // Act
    MockHttpServletResponse response =
        this.mockMvc
            .perform(
                post(ProductController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(productDto)))
            .andReturn()
            .getResponse();

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  void handleNoSuchElementException() throws Exception {
    // Init
    Long id = 1L;

    // Arrange
    doThrow(new NoSuchElementException()).when(this.productService).deleteProduct(id);

    // Act
    MockHttpServletResponse response =
        this.mockMvc
            .perform(delete(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id))
            .andReturn()
            .getResponse();

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    // Verify
    verify(this.productService).deleteProduct(id);
  }

  @Test
  void handleIllegalArgumentException() throws Exception {
    // Init
    Long id = 1L;

    // Arrange
    doThrow(new IllegalArgumentException()).when(this.productService).deleteProduct(id);

    // Act
    MockHttpServletResponse response =
        this.mockMvc
            .perform(delete(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id))
            .andReturn()
            .getResponse();

    // Assert
    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());

    // Verify
    verify(this.productService).deleteProduct(id);
  }

  @Test
  void handleException() throws Exception {
    // Init
    Long id = 1L;

    // Arrange
    doThrow(new RuntimeException()).when(this.productService).deleteProduct(id);

    // Act
    MockHttpServletResponse response =
        this.mockMvc
            .perform(delete(ProductController.BASE_URL + ProductController.ID_URL_VARIABLE, id))
            .andReturn()
            .getResponse();

    // Assert
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

    // Verify
    verify(this.productService).deleteProduct(id);
  }
}
