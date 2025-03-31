package es.rdboboia.custom.starter.extensions;

import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mock;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/**
 * Implements {@link AfterEachCallback} to verify that there are no more
 * interactions than the expected ones which are defined by the when-then
 * instructions.
 */
public class VerifyNoMoreInteractionsExtension implements AfterEachCallback {

	@Override
	public void afterEach(ExtensionContext context) {
		Object testInstance = context.getRequiredTestInstance();
		List<Object> mocks = new ArrayList<>();
		Class<?> clazz = testInstance.getClass();

		// Iterate through all declared fields in the test class.
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Mock.class) || field.isAnnotationPresent(MockitoBean.class)) {
				field.setAccessible(true);
				try {
					mocks.add(field.get(testInstance));
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Failed to access mock field. Exception: {}", e);
				}
			}
		}

		// Verify no more interactions for each mock.
		for (Object mock : mocks) {
			verifyNoMoreInteractions(mock);
		}
	}
}