package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.integration.rest.WiremockRestClient;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.service.ProductService;
import es.rdboboia.custom.starter.service.ProductTagService;
import es.rdboboia.custom.starter.service.RequestRegisterService;
import es.rdboboia.custom.starter.utils.FieldsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** {@link ProductService} implementation. */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  // Internal dependencies (repositories).
  private final ProductRepository productRepository;

  // External dependencies (services).
  private final ProductTagService productTagService;
  private final RequestRegisterService requestRegisterService;

  // External APIs.
  private final WiremockRestClient wiremockRestClient;

  @Override
  public Page<Product> getAllProducts(Product filters, Pageable pageable) {
    return this.productRepository.findAll(Example.of(filters), pageable);
  }

  @Override
  public Product getProductById(Long id) {
    return this.productRepository.findById(id).orElseThrow();
  }

  @Transactional
  @Override
  public Product saveProduct(Product product) {
    this.requestRegisterService.registerRequest(product);

    ResponseEntity<String> method = this.wiremockRestClient.publishProductToWeb();
    System.out.println(method);

    this.productTagService.manageProductTags(product);
    return this.productRepository.save(product);
  }

  @Transactional
  @Override
  public Product updateProduct(Long id, Product product) {
    this.requestRegisterService.registerRequest(product);

    Product productById = this.getProductById(id);
    this.productTagService.manageProductTags(product);
    FieldsUtils.updateIfRequired(productById, product);
    return this.productRepository.save(productById);
  }

  @Override
  public void deleteProduct(Long id) {
    this.productRepository.deleteById(id);
  }
}
