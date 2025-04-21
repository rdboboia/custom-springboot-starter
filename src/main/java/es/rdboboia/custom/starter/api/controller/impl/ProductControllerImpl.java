package es.rdboboia.custom.starter.api.controller.impl;

import es.rdboboia.custom.starter.api.controller.ProductController;
import es.rdboboia.custom.starter.api.dto.ProductDto;
import es.rdboboia.custom.starter.api.mapper.ProductMapper;
import es.rdboboia.custom.starter.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/** {@link ProductController} implementation. */
@RequiredArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @Override
  public List<ProductDto> getAllProducts(ProductDto filters) {
    return this.productMapper.toDto(
        this.productService.getAllProducts(this.productMapper.toEntity(filters)));
  }

  @Override
  public ProductDto getProductById(Long id) {
    return this.productMapper.toDto(this.productService.getProductById(id));
  }

  @Override
  public ProductDto saveProduct(ProductDto productDto) {
    return this.productMapper.toDto(
        this.productService.saveProduct(this.productMapper.toEntity(productDto)));
  }

  @Override
  public ProductDto updateProduct(Long id, ProductDto productDto) {
    return this.productMapper.toDto(
        this.productService.updateProduct(id, this.productMapper.toEntity(productDto)));
  }

  @Override
  public void deleteProduct(Long id) {
    this.productService.deleteProduct(id);
  }
}
