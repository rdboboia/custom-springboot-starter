package es.rdboboia.custom.starter;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

/** Application entry point. */
@Slf4j
@AutoConfigureWireMock
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class BackStarterApplication {

  public static void main(String[] args) {
    log.info("Starting application...");
    SpringApplication.run(BackStarterApplication.class, args);
  }

  @Bean
  Options wireMockOptions() {
    WireMockConfiguration options = WireMockSpring.options();
    options.port(8090);
    return options;
  }
}
