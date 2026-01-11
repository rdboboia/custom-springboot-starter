package es.rdboboia.custom.starter.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration class to set up a WireMock server for mocking HTTP services. The server runs on
 * a port configured via {@link WireMockConfigurationProperties} and uses mappings and files
 * located in the "wiremock" directory on the classpath.
 */
@RequiredArgsConstructor
@Configuration
@Profile(value = {"default"})
public class WireMockServerBeanConfig {

  private final WireMockConfigurationProperties properties;

  @Bean(initMethod = "start", destroyMethod = "stop")
  WireMockServer wireMockServer() {
    return new WireMockServer(
        WireMockConfiguration.options()
            .port(this.properties.getPort())
            .usingFilesUnderClasspath("wiremock"));
  }
}
