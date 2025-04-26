package es.rdboboia.custom.starter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import es.rdboboia.custom.starter.persistence.repository.ProductTypeRepository;
import es.rdboboia.custom.starter.service.impl.ProductTypeServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, VerifyNoMoreInteractionsExtension.class})
class ProductTypeServiceTest {

  @InjectMocks private ProductTypeServiceImpl productTypeServiceImpl;

  @Mock private ProductTypeRepository productTypeRepository;

  @Test
  void getAllProductTypesTest() {
    // Init.
    List<ProductType> productTypes = List.of();

    // Arrange
    when(this.productTypeRepository.findAll()).thenReturn(productTypes);

    // Act
    List<ProductType> allProductTypes = this.productTypeServiceImpl.getAll();

    // Assert
    assertThat(allProductTypes).isNotNull().isEqualTo(productTypes);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void getProductTypeByIdTest() {
    // Init.
    ProductType productType = new ProductType();
    Long id = 1L;

    // Arrange
    when(this.productTypeRepository.findById(id)).thenReturn(java.util.Optional.of(productType));

    // Act
    ProductType productTypeById = this.productTypeServiceImpl.getById(id);

    // Assert
    assertThat(productTypeById).isNotNull().isEqualTo(productType);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void saveProductTypeTest() {
    // Init.
    ProductType productType = new ProductType();

    // Arrange
    when(this.productTypeRepository.save(productType)).thenReturn(productType);

    // Act
    ProductType savedProductType = this.productTypeServiceImpl.save(productType);

    // Assert
    assertThat(savedProductType).isNotNull().isEqualTo(productType);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void updateProductTypeTest() {
    // Init.
    ProductType productType = new ProductType();
    Long id = 1L;

    // Arrange
    when(this.productTypeRepository.findById(id)).thenReturn(java.util.Optional.of(productType));
    when(this.productTypeRepository.save(productType)).thenReturn(productType);

    // Act
    ProductType updatedProductType = this.productTypeServiceImpl.update(id, productType);

    // Assert
    assertThat(updatedProductType).isNotNull().isEqualTo(productType);

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void deleteProductTypeTest() {
    // Init.
    Long id = 1L;

    // Arrange
    doNothing().when(this.productTypeRepository).deleteById(id);

    // Act
    assertDoesNotThrow(() -> this.productTypeServiceImpl.delete(id));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }
}
