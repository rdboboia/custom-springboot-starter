package es.rdboboia.custom.starter.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import es.rdboboia.custom.starter.persistence.repository.ProductTagRepository;
import es.rdboboia.custom.starter.service.impl.ProductTagServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, VerifyNoMoreInteractionsExtension.class})
class ProductTagServiceTest {

  @InjectMocks private ProductTagServiceImpl productTagServiceImpl;

  @Mock private ProductTagRepository productTagRepository;

  @Test
  void manageProductTagsNullTagsListTest() {
    // Init.
    Product product = new Product();

    // Act & Assert.
    assertDoesNotThrow(() -> this.productTagServiceImpl.manageProductTags(product));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void manageProductTagsEmptyTagsListTest() {
    // Init.
    Product product = new Product();
    product.setTags(List.of());

    // Act & Assert.
    assertDoesNotThrow(() -> this.productTagServiceImpl.manageProductTags(product));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void manageProductTagsExistingTagsTest() {
    // Init.
    String tag = "tag1";
    ProductTag tag1 = ProductTag.builder().name(tag).build();

    Product product = new Product();
    product.setTags(List.of(tag1));

    // Arrange.
    when(this.productTagRepository.findByName("tag1")).thenReturn(Optional.of(tag1));

    // Act & Assert.
    assertDoesNotThrow(() -> this.productTagServiceImpl.manageProductTags(product));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }

  @Test
  void manageProductTagsNewTagsTest() {
    // Init.
    String tag = "tag1";
    ProductTag tag1 = ProductTag.builder().name(tag).build();

    Product product = new Product();
    product.setTags(List.of(tag1));

    // Arrange.
    when(this.productTagRepository.findByName("tag1")).thenReturn(Optional.empty());
    when(this.productTagRepository.save(tag1)).thenReturn(tag1);

    // Act & Assert.
    assertDoesNotThrow(() -> this.productTagServiceImpl.manageProductTags(product));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }
}
