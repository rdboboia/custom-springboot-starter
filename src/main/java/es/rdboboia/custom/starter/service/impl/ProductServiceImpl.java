package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.service.ProductService;
import es.rdboboia.custom.starter.utils.FieldsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** {@link ProductService} implementation. */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public Page<Product> getAllProducts(Product filters, Pageable pageable) {
    return this.productRepository.findAll(Example.of(filters), pageable);
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
