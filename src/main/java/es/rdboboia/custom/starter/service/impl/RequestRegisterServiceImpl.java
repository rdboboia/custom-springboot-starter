package es.rdboboia.custom.starter.service.impl;

import es.rdboboia.custom.starter.persistence.entity.Product;
import es.rdboboia.custom.starter.persistence.entity.Request;
import es.rdboboia.custom.starter.persistence.repository.RequestRepository;
import es.rdboboia.custom.starter.service.RequestRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** {@link RequestRegisterService} implementation. */
@RequiredArgsConstructor
@Service
public class RequestRegisterServiceImpl implements RequestRegisterService {

  // Internal dependencies (repositories).
  private final RequestRepository requestRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public void registerRequest(Product product) {
    Request request = Request.builder().data(product.toString()).build();
    this.requestRepository.save(request);
  }
}
