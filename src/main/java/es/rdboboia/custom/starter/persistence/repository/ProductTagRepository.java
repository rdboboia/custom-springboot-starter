package es.rdboboia.custom.starter.persistence.repository;

import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** {@link ProductTag} repository. */
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

  Optional<ProductTag> findByName(String name);
}
