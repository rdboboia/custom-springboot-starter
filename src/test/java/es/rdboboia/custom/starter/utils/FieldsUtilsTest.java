package es.rdboboia.custom.starter.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.Builder;
import lombok.Data;

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
		assertThrows(IllegalStateException.class, () -> FieldsUtils.updateIfRequired(null, null),
				"This execution should have thrown an exception");
	}

	@Test
	void updateIfRequiredTest() {
		TestClass target = TestClass.builder().notes("some notes").build();
		TestClass source = TestClass.builder().id(1L).idAtStart(1L).code("CODE").description("DESC").build();

		FieldsUtils.updateIfRequired(target, source);

		assertNull(target.getId());
		assertNull(target.getIdAtStart());
		assertEquals("CODE", target.getCode());
		assertEquals("DESC", target.getDescription());
		assertEquals("some notes", target.getNotes());
	}
}
