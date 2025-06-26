package es.rdboboia.custom.starter.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.repository.RequestRepository;
import es.rdboboia.custom.starter.service.impl.RequestRegisterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, VerifyNoMoreInteractionsExtension.class})
class RequestRegisterServiceTest {

  @InjectMocks private RequestRegisterServiceImpl requestRegisterServiceImpl;

  @Mock private RequestRepository requestRepository;

  @Test
  void registerRequestTest() {
    // Init.
    Object object = new Object();

    // Arrange.
    when(this.requestRepository.save(any())).thenReturn(null);

    // Act & Assert.
    assertDoesNotThrow(() -> this.requestRegisterServiceImpl.registerRequest(object));

    // Verifies automatically done by VerifyNoMoreInteractionsExtension.
  }
}
