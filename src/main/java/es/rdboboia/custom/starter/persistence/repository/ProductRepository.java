package es.rdboboia.custom.starter.persistence.repository;

import es.rdboboia.custom.starter.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** {@link Product} repository. */
public interface ProductRepository
    extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  @Override
  @EntityGraph(attributePaths = {Product.Fields.type, Product.Fields.tags})
  Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
