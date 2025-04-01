package es.rdboboia.custom.starter.api.error.dto;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response error DTO. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseErrorDto {

  private ZonedDateTime timestamp;
  private String message;
  private String details;
}
