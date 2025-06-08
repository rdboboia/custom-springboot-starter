package es.rdboboia.custom.starter.persistence.specification;

import es.rdboboia.custom.starter.persistence.entity.Product;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/** Specification for filtering {@link Product} by its fields. */
@Slf4j
public class ProductSpecificationV2 {

  private ProductSpecificationV2() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Applies all non-null filters from the given {@link Product} object.
   *
   * @param filters The {@link Product} object containing filter criteria.
   * @return A {@link Specification} that applies all non-null filters.
   */
  public static <T> Specification<T> applyAllNonNullFilters(T filters) {
    return applyAllNonNullFilters(filters, List.of());
  }

  /**
   * Applies all non-null filters from the given {@link Product} object.
   *
   * @param filters The {@link Product} object containing filter criteria.
   * @param fieldsToIgnore A list of field names to ignore during filtering.
   * @return A {@link Specification} that applies all non-null filters.
   */
  public static <T> Specification<T> applyAllNonNullFilters(
      T filters, List<String> fieldsToIgnore) {

    Specification<T> specification = Specification.where(null);
    try {
      List<Field> fieldsToUpdate =
          Arrays.stream(filters.getClass().getDeclaredFields())
              .filter(e -> !Modifier.isPublic(e.getModifiers()))
              .filter(e -> !Modifier.isStatic(e.getModifiers()))
              .filter(e -> !Modifier.isFinal(e.getModifiers()))
              .filter(e -> !fieldsToIgnore.contains(e.getName()))
              .toList();

      for (Field f : fieldsToUpdate) {
        String capitalizedMethod = StringUtils.capitalize(f.getName());
        Method getter = filters.getClass().getMethod("get" + capitalizedMethod);
        Object value = getter.invoke(filters);

        if (value != null && !isJpaRelatedField(f)) {
          specification =
              specification.and(
                  (root, query, criteriaBuilder) -> {
                    String fieldName = f.getName();
                    if (f.getType().equals(String.class)) {
                      return criteriaBuilder.like(
                          criteriaBuilder.lower(root.get(fieldName)),
                          "%" + value.toString().toLowerCase() + "%");
                    } else {
                      return criteriaBuilder.equal(root.get(fieldName), value);
                    }
                  });
        }
      }
    } catch (Exception e) {
      log.error("Entity could not be updated. Exception: {}", e);
      throw new IllegalStateException("Entity could not be updated. Exception: {}", e);
    }

    return specification;
  }

  private static boolean isJpaRelatedField(Field field) {
    return field.isAnnotationPresent(OneToOne.class)
        || field.isAnnotationPresent(ManyToOne.class)
        || field.isAnnotationPresent(OneToMany.class)
        || field.isAnnotationPresent(ManyToMany.class);
  }
}
