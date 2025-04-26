package es.rdboboia.custom.starter.service;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import java.util.List;

/** {@link ProductType} service interface for CRUD operations. */
public interface ProductTypeService {

  List<ProductType> getAll();

  ProductType getById(Long id);

  ProductType save(ProductType productType);

  ProductType update(Long id, ProductType productType);

  void delete(Long id);
}
