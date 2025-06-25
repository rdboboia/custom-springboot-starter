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
  private final ProductTagRepository productTagRepository;

  @Override
  public void manageProductTags(Product product) {
    List<ProductTag> tags = new ArrayList<>();
    for (ProductTag tag : product.getTags()) {
      Optional<ProductTag> byName = this.productTagRepository.findByName(tag.getName());

      if (byName.isPresent()) {
        ProductTag existingTag = byName.get();
        tags.add(existingTag);
      } else {
        this.productTagRepository.save(tag);
        tags.add(tag);
      }
    }

    product.setTags(tags);
  }
}
