package es.rdboboia.custom.starter.integration.rest;

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

  /**
   * Method to perform a REST API call using a RestClient.
   *
   * @return ResponseEntity containing the response body and status code.
   */
  public ResponseEntity<String> publishProductToWeb() {
    log.debug("Creating client...");
    RestClient client = RestClient.builder().baseUrl("http://localhost:8090").build();

    log.debug("Performing API call...");
    ResponseEntity<String> response = client.get().uri("/hello").retrieve().toEntity(String.class);

    log.debug("Response code: {}", response.getStatusCode());
    log.debug("Response body: {}", response.getBody());

    return response;
  }
}
