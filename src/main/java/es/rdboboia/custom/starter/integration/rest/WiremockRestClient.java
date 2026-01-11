package es.rdboboia.custom.starter.integration.rest;

import es.rdboboia.custom.starter.persistence.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * A simple REST client that performs an API call to a Wiremock server. This client is used to
 * demonstrate how to make REST calls in a Spring application.
 */
@Slf4j
@Component
public class WiremockRestClient {
  private final RestClient client = RestClient.builder().baseUrl("http://localhost:8090").build();

  /**
   * Method to perform a REST API call using a RestClient.
   *
   * @param product The {@link Product} to be published.
   * @return ResponseEntity containing the response body and status code.
   */
  public ResponseEntity<String> publishProductToWeb(Product product) {
    log.debug("Performing API call...");
    ResponseEntity<String> response =
        this.client
            .post()
            .uri("/sync/product/{productId}", product.getId())
            .retrieve()
            .toEntity(String.class);

    log.debug("Response code: {}", response.getStatusCode());
    log.debug("Response body: {}", response.getBody());

    this.apiCallFail();

    return response;
  }

  private void apiCallFail() {

    try {
      log.debug("Performing API call...");
      this.client.get().uri("/fail").retrieve().toEntity(String.class);
    } catch (Exception e) {
      log.error("An error occurred during the API call simulation: {}", e.getMessage());
      log.warn("Skipping the error as this is a simulated failure for demonstration purposes.");
    }
  }
}
