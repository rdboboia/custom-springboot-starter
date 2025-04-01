package es.rdboboia.custom.starter.persistence.repository;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** {@link ProductType} repository. */
public interface ProductTypeRepository
    extends JpaRepository<ProductType, Long>, JpaSpecificationExecutor<ProductType> {}
