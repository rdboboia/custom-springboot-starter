package es.rdboboia.custom.starter.persistence.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Generic repository that provides a "shortcut" to extend {@link JpaRepository} and {@link
 * JpaSpecificationExecutor}.
 */
public interface GenericBaseRepository<T, K>
    extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {}
