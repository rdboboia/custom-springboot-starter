package es.rdboboia.custom.starter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.service.impl.ProductServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith({MockitoExtension.class, VerifyNoMoreInteractionsExtension.class})
class ProductServiceTest {

  @InjectMocks private ProductServiceImpl productServiceImpl;

  @Mock private ProductRepository productRepository;

  @Test
  void getAllProductsTest() {
    // Init.
    Product filters = new Product();
    List<Product> products = List.of();

    // Arrange
    when(this.productRepository.findAll(Example.of(filters))).thenReturn(products);

    // Act
    List<Product> allProducts = this.productServiceImpl.getAllProducts(filters);

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

    // Arrange
    when(this.productRepository.save(product)).thenReturn(product);

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
    when(this.productRepository.findById(id)).thenReturn(Optional.of(productById));
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
