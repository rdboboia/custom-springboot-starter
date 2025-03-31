package es.rdboboia.custom.starter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.service.ProductService;
import es.rdboboia.custom.starter.utils.FieldsUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return this.productRepository.findById(id).orElseThrow();
	}

	@Override
	public Product saveProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product productById = this.getProductById(id);
		FieldsUtils.updateIfRequired(productById, product);
		return this.productRepository.save(productById);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

}
