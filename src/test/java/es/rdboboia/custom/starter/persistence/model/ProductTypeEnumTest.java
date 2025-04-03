package es.rdboboia.custom.starter.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import org.junit.jupiter.api.Test;

class ProductTypeEnumTest {

  @Test
  void getEntityTest() {
    // Init
    ProductTypeEnum productTypeEnum = ProductTypeEnum.FOOD;

    // Act
    ProductType result = productTypeEnum.getEntity();

    // Assert
    assertEquals(productTypeEnum.getId(), result.getId());
    assertEquals(productTypeEnum.getName(), result.getName());
  }

  @Test
  void getEnumFromIdOkTest() {
    // Init
    ProductTypeEnum productTypeEnum = ProductTypeEnum.FOOD;

    // Act
    ProductTypeEnum result = ProductTypeEnum.getEnumFromId(productTypeEnum.getId());

    // Assert
    assertEquals(productTypeEnum, result);
  }

  @Test
  void getEnumFromIdExceptionTest() {
    // Init
    Long id = 4L;

    // Act & Assert

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ProductTypeEnum.getEnumFromId(id);
        });
  }
}
