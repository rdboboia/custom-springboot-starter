package es.rdboboia.custom.starter.api.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import es.rdboboia.custom.starter.persistence.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

class ProductMapperTest {

  private ProductMapperImpl productMapperImpl = new ProductMapperImpl();

  @Test
  void toDtoTest() {
    // Init.
    Page<Product> products = Page.empty();

    // Act.
    assertDoesNotThrow(() -> this.productMapperImpl.toDto(products));
  }
}
