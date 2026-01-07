package es.rdboboia.custom.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

/** Application entry point. */
@Slf4j
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class BackStarterApplication {

  public static void main(String[] args) {
    log.info("Starting application...");
    SpringApplication.run(BackStarterApplication.class, args);
  }
}
