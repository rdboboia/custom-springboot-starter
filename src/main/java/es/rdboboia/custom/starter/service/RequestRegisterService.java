package es.rdboboia.custom.starter.service;

import es.rdboboia.custom.starter.persistence.entity.Product;

/** {@link Product} service for atomic transaction management. */
public interface RequestRegisterService {

  void registerRequest(Product product);
}
