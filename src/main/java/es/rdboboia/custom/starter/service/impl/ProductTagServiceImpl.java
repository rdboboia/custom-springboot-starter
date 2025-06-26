package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.ProductTag;
import es.rdboboia.custom.starter.persistence.repository.ProductTagRepository;
import es.rdboboia.custom.starter.service.ProductTagService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** {@link ProductTagService} implementation. */
@RequiredArgsConstructor
@Service
public class ProductTagServiceImpl implements ProductTagService {

  // Internal dependencies (repositories).
  private final ProductTagRepository productTagRepository;

  @Override
  public void manageProductTags(Product product) {
    if (product.getTags() != null) {

      List<ProductTag> tags = new ArrayList<>();
      for (ProductTag tag : product.getTags()) {
        Optional<ProductTag> byName = this.productTagRepository.findByName(tag.getName());

        if (byName.isPresent()) {
          ProductTag existingTag = byName.get();
          tags.add(existingTag);
        } else {
          ProductTag savedTag = this.productTagRepository.save(tag);
          tags.add(savedTag);
        }
      }

      product.setTags(tags);
    }
  }
}
