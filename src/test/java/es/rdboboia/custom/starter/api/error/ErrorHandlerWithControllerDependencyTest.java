package es.rdboboia.custom.starter.api.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import es.rdboboia.custom.starter.api.controller.ProductController;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.service.ProductService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@ExtendWith({VerifyNoMoreInteractionsExtension.class})
class ErrorHandlerWithControllerDependencyTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductService productService;
  @MockitoBean private ProductMapper productMapper;

  @Test
  void noSuchElementExceptionTest() throws Exception {
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

    System.out.println(response.getContentAsString());

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    // Verify
    verify(this.productService).deleteProduct(id);
  }
}
