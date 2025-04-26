package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import es.rdboboia.custom.starter.persistence.repository.ProductTypeRepository;
import es.rdboboia.custom.starter.service.ProductTypeService;
import es.rdboboia.custom.starter.utils.FieldsUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** {@link ProductTypeService} implementation. */
@RequiredArgsConstructor
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
  private final ProductTypeRepository productTypeRepository;

  @Override
  public List<ProductType> getAll() {
    return this.productTypeRepository.findAll();
  }

  @Override
  public ProductType getById(Long id) {
    return this.productTypeRepository.findById(id).orElseThrow();
  }

  @Override
  public ProductType save(ProductType productType) {
    return this.productTypeRepository.save(productType);
  }

  @Override
  public ProductType update(Long id, ProductType productType) {
    ProductType productTypeById = this.getById(id);
    FieldsUtils.updateIfRequired(productTypeById, productType);
    return this.productTypeRepository.save(productTypeById);
  }

  @Override
  public void delete(Long id) {
    this.productTypeRepository.deleteById(id);
  }
}
