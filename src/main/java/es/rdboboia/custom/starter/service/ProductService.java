package es.rdboboia.custom.starter.service;

import java.util.List;

import es.rdboboia.custom.starter.persistence.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product saveProduct(Product product);

	Product updateProduct(Long id, Product product);

	void deleteProduct(Long id);

}
