package es.rdboboia.custom.starter.persistence.repository;

import es.rdboboia.custom.starter.persistence.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/** {@link Request} repository. */
public interface RequestRepository extends JpaRepository<Request, Long> {}
