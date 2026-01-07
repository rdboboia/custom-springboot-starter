package es.rdboboia.custom.starter.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to set up a WireMock server for mocking HTTP services. The server is
 * configured to run on port 8090 and uses mappings and files located in the "wiremock" directory on
 * the classpath.
 */
@Configuration
public class WireMockConfig {

  @Bean(initMethod = "start", destroyMethod = "stop")
  WireMockServer wireMockServer() {
    return new WireMockServer(
        WireMockConfiguration.options().port(8090).usingFilesUnderClasspath("wiremock"));
  }
}
