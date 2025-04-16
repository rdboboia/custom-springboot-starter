package es.rdboboia.custom.starter.persistence.repository;

import es.rdboboia.custom.starter.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/** {@link Product} repository. */
public interface ProductRepository extends JpaRepository<Product, Long> {}
