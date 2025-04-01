package es.rdboboia.custom.starter.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/** This utility class provides methods to work with class fields (updates and null checks). */
@Slf4j
public class FieldsUtils {

  /** Utility class. No instances allowed */
  private FieldsUtils() {
    // Private constructor.
  }

  /**
   * Updates non ID fields if they are not null.
   *
   * @param <T> the {@link Class} type of the arguments.
   * @param target the target where the fields will be stored to.
   * @param source the source where the fields will be retrieved from.
   */
  public static <T> void updateIfRequired(T target, T source) {
    try {
      Map<String, Method> methodMap =
          Arrays.stream(target.getClass().getDeclaredMethods())
              .collect(Collectors.toUnmodifiableMap(Method::getName, method -> method));

      List<Field> fieldsToUpdate =
          Arrays.stream(target.getClass().getDeclaredFields())
              .filter(e -> !Modifier.isPublic(e.getModifiers()))
              .filter(e -> !Modifier.isStatic(e.getModifiers()))
              .filter(e -> !Modifier.isFinal(e.getModifiers()))
              .filter(e -> !e.getName().startsWith("id"))
              .toList();

      for (Field f : fieldsToUpdate) {
        String capitalizedMethod = StringUtils.capitalize(f.getName());
        Method getter = source.getClass().getMethod("get" + capitalizedMethod);
        Object value = getter.invoke(source);

        if (value != null) {
          Method setter = methodMap.get("set" + capitalizedMethod);
          setter.invoke(target, value);
        }
      }
    } catch (Exception e) {
      log.error("Entity could not be updated. Exception: {}", e);
      throw new IllegalStateException("Entity could not be updated. Exception: {}", e);
    }
  }
}
