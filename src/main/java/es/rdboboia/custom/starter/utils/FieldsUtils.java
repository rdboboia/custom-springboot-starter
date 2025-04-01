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
   * Updates any non public, static and final fields of the {@code target} object if they are not
   * null.
   *
   * @param <T> the {@link Class} type of the arguments.
   * @param target the target where the fields will be stored to.
   * @param source the source where the fields will be retrieved from.
   */
  public static <T> void updateIfRequired(T target, T source) {
    updateIfRequired(target, source, List.of());
  }

  /**
   * Updates any non public, static and final fields of the {@code target} object if they are not
   * null and if they are not in the {@code fieldsToIgnore} list.
   *
   * @param <T> the {@link Class} type of the arguments.
   * @param target the target where the fields will be stored to.
   * @param source the source where the fields will be retrieved from.
   * @param fieldsToIgnore the list of fields to ignore.
   */
  public static <T> void updateIfRequired(T target, T source, List<String> fieldsToIgnore) {
    try {
      Map<String, Method> methodMap =
          Arrays.stream(target.getClass().getDeclaredMethods())
              .collect(Collectors.toUnmodifiableMap(Method::getName, method -> method));

      List<Field> fieldsToUpdate =
          Arrays.stream(target.getClass().getDeclaredFields())
              .filter(e -> !Modifier.isPublic(e.getModifiers()))
              .filter(e -> !Modifier.isStatic(e.getModifiers()))
              .filter(e -> !Modifier.isFinal(e.getModifiers()))
              .filter(e -> !fieldsToIgnore.contains(e.getName()))
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

  /**
   * Checks if any of the non <code>public, static and final</code> fields of an object is null.
   *
   * @param <T> the {@link Class} type of the arguments.
   * @param target the object to check.
   * @return true if any checked field is null; false only and only if all checked fields are not
   *     null.
   */
  public static <T> boolean isAnyFieldNull(T target) {
    try {
      List<Field> fieldsToCheck =
          Arrays.stream(target.getClass().getDeclaredFields())
              .filter(e -> !Modifier.isPublic(e.getModifiers()))
              .filter(e -> !Modifier.isStatic(e.getModifiers()))
              .filter(e -> !Modifier.isFinal(e.getModifiers()))
              .toList();

      for (Field f : fieldsToCheck) {
        String capitalizedMethod = StringUtils.capitalize(f.getName());
        Method getter = target.getClass().getMethod("get" + capitalizedMethod);

        if (getter.invoke(target) == null) {
          return true;
        }
      }

      return false;
    } catch (Exception e) {
      log.error("Entity could not be checked. Exception: {}", e);
      throw new IllegalStateException("Entity could not be checked. Exception: {}", e);
    }
  }
}
