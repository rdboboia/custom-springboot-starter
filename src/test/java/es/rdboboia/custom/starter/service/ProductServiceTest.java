package es.rdboboia.custom.starter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.integration.rest.WireMockRestClient;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.service.impl.ProductServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@ExtendWith({MockitoExtension.class, VerifyNoMoreInteractionsExtension.class})
class ProductServiceTest {

  @InjectMocks private ProductServiceImpl productServiceImpl;

  // Internal dependencies (repositories).
  @Mock private ProductRepository productRepository;

  // External dependencies (services).
  @Mock private ProductTagService productTagService;
  @Mock private RequestRegisterService requestRegisterService;

  // External APIs.
  @Mock private WireMockRestClient wiremockRestClient;

  @Test
  void getAllProductsTest() {
    // Init.
    Product filters = new Product();
    Page<Product> products = Page.empty();
    Pageable pageable = Pageable.unpaged();

    // Arrange
    when(this.productRepository.findAll(Example.of(filters), pageable)).thenReturn(products);

    // Act
    Page<Product> allProducts = this.productServiceImpl.getAllProducts(filters, pageable);

    // Assert
    assertThat(allProducts).isNotNull().isEqualTo(products);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void getProductByIdTest() {
    // Init.
    Product product = new Product();
    Long id = 1L;

    // Arrange
    when(this.productRepository.findById(id)).thenReturn(java.util.Optional.of(product));

    // Act
    Product productById = this.productServiceImpl.getProductById(id);

    // Assert
    assertThat(productById).isNotNull().isEqualTo(product);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void saveProductTest() {
    // Init.
    Product product = new Product();
    ResponseEntity<String> responseEntity = ResponseEntity.ok("Success");

    // Arrange
    doNothing().when(this.requestRegisterService).registerRequest(product);
    doNothing().when(this.productTagService).manageProductTags(product);
    when(this.productRepository.save(product)).thenReturn(product);
    when(this.wiremockRestClient.publishProductToWeb(product)).thenReturn(responseEntity);

    // Act
    Product savedProduct = this.productServiceImpl.saveProduct(product);

    // Assert
    assertThat(savedProduct).isNotNull().isEqualTo(product);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void updateProductTest() {
    // Init.
    Long id = 1L;
    Product productById = Product.builder().id(id).build();
    Product product = Product.builder().name("name").build();

    // Arrange
    doNothing().when(this.requestRegisterService).registerRequest(product);
    when(this.productRepository.findById(id)).thenReturn(Optional.of(productById));
    doNothing().when(this.productTagService).manageProductTags(product);
    when(this.productRepository.save(productById)).thenReturn(productById);

    // Act
    Product updatedProduct = this.productServiceImpl.updateProduct(id, product);

    // Assert
    assertThat(updatedProduct).isNotNull().isEqualTo(productById);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void deleteProductTest() {
    // Init.
    Long id = 1L;

    // Arrange
    doNothing().when(this.productRepository).deleteById(id);

    // Act
    assertDoesNotThrow(() -> this.productServiceImpl.deleteProduct(id));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }
}
