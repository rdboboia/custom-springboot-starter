package es.rdboboia.custom.starter.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import org.junit.jupiter.api.Test;

class ProductTagMapperTest {

  private ProductTagMapperImpl productTagMapperImpl = new ProductTagMapperImpl();

  @Test
  void toDtoNullTest() {
    // Init.
    ProductTag productTag = null;

    // Act.
    String dto = this.productTagMapperImpl.toDto(productTag);

    // Assert.
    assertEquals(null, dto);
  }

  @Test
  void toDtoTest() {
    // Init.
    ProductTag productTag = new ProductTag();
    productTag.setName("test");

    // Act.
    String dto = this.productTagMapperImpl.toDto(productTag);

    // Assert.
    assertEquals("test", dto);
  }
}
