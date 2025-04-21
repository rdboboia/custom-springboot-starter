package es.rdboboia.custom.starter.api.error.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response error DTO. */
@Schema(description = "Response error object")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseErrorDto {

  @Schema(description = "Timestamp of the error", example = "2025-04-21T18:41:23.423Z")
  private ZonedDateTime timestamp;

  @Schema(description = "Error message", example = "Some error message")
  private String message;

  @Schema(description = "Error details", example = "Some error details")
  private String details;

  private List<String> errors;
}
