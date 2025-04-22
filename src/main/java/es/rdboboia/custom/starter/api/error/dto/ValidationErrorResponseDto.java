package es.rdboboia.custom.starter.api.error.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** Response error DTO. */
@Schema(description = "Response error object")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ValidationErrorResponseDto extends BaseErrorResponseDto {

  @Schema(
      description = "List of validation errors",
      example = "[\"name must not be blank\", \"price must be greater than 0\"]")
  private List<String> errors;
}
