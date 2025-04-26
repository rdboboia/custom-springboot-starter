package es.rdboboia.custom.starter.api.controller.impl;

import es.rdboboia.custom.starter.api.controller.ProductTypeController;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypePostDto;
import es.rdboboia.custom.starter.api.mapper.ProductTypeMapper;
import es.rdboboia.custom.starter.service.ProductTypeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/** {@link ProductTypeController} implementation. */
@RequiredArgsConstructor
@RestController
public class ProductTypeControllerImpl implements ProductTypeController {

  private final ProductTypeService productTypeService;
  private final ProductTypeMapper productTypeMapper;

  @Override
  public List<ProductTypeDto> getAll() {
    return this.productTypeMapper.toDto(this.productTypeService.getAll());
  }

  @Override
  public ProductTypeDto save(@Valid ProductTypePostDto productTypeDto) {
    return this.productTypeMapper.toDto(
        this.productTypeService.save(this.productTypeMapper.toEntity(productTypeDto)));
  }

  @Override
  public ProductTypeDto update(Long id, @Valid ProductTypePostDto productTypeDto) {
    return this.productTypeMapper.toDto(
        this.productTypeService.update(id, this.productTypeMapper.toEntity(productTypeDto)));
  }

  @Override
  public void delete(Long id) {
    this.productTypeService.delete(id);
  }
}
