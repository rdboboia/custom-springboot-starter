package es.rdboboia.custom.starter.service;

import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.ProductTag;

/** {@link ProductTag} service interface for CRUD operations. */
public interface ProductTagService {

  void manageProductTags(Product product);
}
