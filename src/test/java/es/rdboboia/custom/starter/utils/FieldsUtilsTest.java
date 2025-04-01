package es.rdboboia.custom.starter.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FieldsUtilsTest {

  @Data
  @Builder
  private static class TestClass implements Serializable {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    public static final long FAILING_FIELD = 1L;

    private final long otherField = 1L;

    private Long id;
    private Long idAtStart;
    private String code;
    private String description;
    private String notes;
  }

  /* ****************** */
  /* UPDATE IF REQUIRED */
  /* ****************** */

  @Test
  void updateIfRequiredNullParamsTest() {
    // Act & Assert
    assertThrows(
        IllegalStateException.class,
        () -> FieldsUtils.updateIfRequired(null, null),
        "This execution should have thrown an exception");
  }

  @Test
  void updateIfRequiredTest() {
    // Init
    TestClass target = TestClass.builder().notes("some notes").build();
    TestClass source =
        TestClass.builder().id(1L).idAtStart(1L).code("CODE").description("DESC").build();

    // Act
    FieldsUtils.updateIfRequired(target, source);

    // Assert
    assertEquals(1L, target.getId());
    assertEquals(1L, target.getIdAtStart());
    assertEquals("CODE", target.getCode());
    assertEquals("DESC", target.getDescription());
    assertEquals("some notes", target.getNotes());
  }

  @Test
  void updateIfRequiredWithExcludedFieldsTest() {
    // Init
    TestClass target = TestClass.builder().notes("some notes").build();
    TestClass source =
        TestClass.builder().id(1L).idAtStart(1L).code("CODE").description("DESC").build();
    List<String> fieldsToExclude = List.of("id", "idAtStart");

    // Act
    FieldsUtils.updateIfRequired(target, source, fieldsToExclude);

    // Assert
    assertNull(target.getId());
    assertNull(target.getIdAtStart());
    assertEquals("CODE", target.getCode());
    assertEquals("DESC", target.getDescription());
    assertEquals("some notes", target.getNotes());
  }

  /* ***************** */
  /* IS ANY FIELD NULL */
  /* ***************** */

  @Test
  void isAnyFieldNullNullParamsTest() {
    // Act & Assert
    assertThrows(
        IllegalStateException.class,
        () -> FieldsUtils.isAnyFieldNull(null),
        "This execution should have thrown an exception");
  }

  private static Stream<Arguments> filterPairNullCountTestGenerator() {
    TestClass allFieldsNull = TestClass.builder().build();

    TestClass oneFieldNull =
        TestClass.builder().id(1L).idAtStart(1L).code("CODE").description("DESC").build();

    TestClass noNullField =
        TestClass.builder()
            .id(1L)
            .idAtStart(1L)
            .code("CODE")
            .description("DESC")
            .notes("NOTES")
            .build();

    return Stream.of(
        Arguments.of(allFieldsNull, true),
        Arguments.of(oneFieldNull, true),
        Arguments.of(noNullField, false));
  }

  @ParameterizedTest
  @MethodSource("filterPairNullCountTestGenerator")
  void filterPairNullCountTest(Object object, boolean expectedResult) {
    // Act
    boolean result = FieldsUtils.isAnyFieldNull(object);

    // Assert
    assertEquals(expectedResult, result);
  }
}
