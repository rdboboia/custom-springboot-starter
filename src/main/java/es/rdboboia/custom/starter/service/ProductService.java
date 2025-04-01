package es.rdboboia.custom.starter.service;

import es.rdboboia.custom.starter.persistence.entity.Product;
import java.util.List;

/** {@link Product} service interface for CRUD operations. */
public interface ProductService {

  List<Product> getAllProducts();

  Product getProductById(Long id);

  Product saveProduct(Product product);

  Product updateProduct(Long id, Product product);

  void deleteProduct(Long id);
}
