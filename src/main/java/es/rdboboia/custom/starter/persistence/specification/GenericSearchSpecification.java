package es.rdboboia.custom.starter.persistence.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Generic specification for searching entities by multiple columns.
 *
 * @param <T> The type of the entity to search.
 */
@RequiredArgsConstructor
public class GenericSearchSpecification<T> implements Specification<T> {
  private static final long serialVersionUID = 1L;

  private final List<String> columns;
  private final String search;

  @Override
  public Predicate toPredicate(
      Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

    List<Predicate> predicates = new ArrayList<>();
    for (String column : this.columns) {
      Expression<String> expression = this.buildExpression(column, root);
      Predicate predicate =
          criteriaBuilder.like(
              criteriaBuilder.upper(expression), "%" + this.search.toUpperCase() + "%");
      predicates.add(predicate);
    }

    return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
  }

  private Expression<String> buildExpression(String column, Path<T> path) {
    if (!column.contains(".")) {
      return path.get(column);
    }

    String[] parts = column.split("\\.");
    int i = 0;
    for (; i < parts.length - 1; i++) {
      path = path.get(parts[i]);
    }

    return path.get(parts[i]);
  }
}
