package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.integration.rest.WireMockRestClient;
import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.repository.ProductRepository;
import es.rdboboia.custom.starter.persistence.specification.ProductSpecificationV2;
import es.rdboboia.custom.starter.service.ProductService;
import es.rdboboia.custom.starter.service.ProductTagService;
import es.rdboboia.custom.starter.service.RequestRegisterService;
import es.rdboboia.custom.starter.utils.FieldsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** {@link ProductService} implementation. */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  // Internal dependencies (repositories).
  private final ProductRepository productRepository;

  // External dependencies (services).
  private final ProductTagService productTagService;
  private final RequestRegisterService requestRegisterService;

  // External APIs.
  private final WireMockRestClient wireMockRestClient;

  @Override
  public Page<Product> getAllProducts(Product filters, Pageable pageable) {
    //    return this.productRepository.findAll(Example.of(filters), pageable);
    Specification<Product> applyAllNonNullFilters =
        ProductSpecificationV2.applyAllNonNullFilters(filters);

    return this.productRepository.findAll(applyAllNonNullFilters, pageable);
  }

  @Override
  public Product getProductById(Long id) {
    return this.productRepository.findById(id).orElseThrow();
  }

  @Transactional
  @Override
  public Product saveProduct(Product product) {
    this.requestRegisterService.registerRequest(product);

    this.productTagService.manageProductTags(product);
    Product savedProduct = this.productRepository.save(product);

    ResponseEntity<String> apiResponse = this.wireMockRestClient.publishProductToWeb(savedProduct);
    log.debug(
        "Published product with id {} to external API. Response code: {}, body: {}",
        savedProduct.getId(),
        apiResponse.getStatusCode(),
        apiResponse.getBody());

    return savedProduct;
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
