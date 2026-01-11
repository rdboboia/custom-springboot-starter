package es.rdboboia.custom.starter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** Configuration properties for WireMock server. */
@ConfigurationProperties(prefix = "wiremock.server")
@Configuration
@Data
public class WiremockConfigurationProperties {
  private int port;
}
