package es.rdboboia.custom.starter.service;

import es.rdboboia.custom.starter.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** {@link Product} service interface for CRUD operations. */
public interface ProductService {

  Page<Product> getAllProducts(Product filters, Pageable pageable);

  Page<Product> getAllProductsWithCriteria(Product filters, Pageable pageable);

  Page<Product> getAllWithCriteria(Product filters, Pageable pageable);

  Product getProductById(Long id);

  Product saveProduct(Product product);

  Product updateProduct(Long id, Product product);

  void deleteProduct(Long id);
}
