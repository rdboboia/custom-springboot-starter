package es.rdboboia.custom.starter.api.error;

import es.rdboboia.custom.starter.api.error.dto.BaseErrorResponseDto;
import es.rdboboia.custom.starter.api.error.dto.ValidationErrorResponseDto;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/** REST controller error handler. */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  /* *********************** */
  /* PRIVATE UTILITY METHODS */
  /* *********************** */

  private BaseErrorResponseDto getErrorDto(Exception exception, WebRequest request) {
    this.logError(exception, request);

    return BaseErrorResponseDto.builder()
        .timestamp(ZonedDateTime.now())
        .message(exception.getMessage())
        .details(exception.getStackTrace()[1].toString())
        .build();
  }

  private ValidationErrorResponseDto getValidationErrorDto(
      MethodArgumentNotValidException exception, WebRequest request) {

    this.logError(exception, request);

    List<String> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + " " + e.getDefaultMessage())
            .toList();

    return ValidationErrorResponseDto.builder()
        .timestamp(ZonedDateTime.now())
        .message("Validation error")
        .details("There are validation errors in the request")
        .errors(errors)
        .build();
  }

  private void logError(Exception exception, WebRequest request) {
    log.error(
        "Exception occurred: {}. Request URL: {}",
        exception.getMessage(),
        request.getDescription(false));

    log.debug("Exception stack trace: {}", exception);
  }

  /* ****************** */
  /* EXCEPTION HANDLERS */
  /* ****************** */

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrorResponseDto handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception, WebRequest request) {
    return this.getValidationErrorDto(exception, request);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseErrorResponseDto handleNoSuchElementException(
      NoSuchElementException exception, WebRequest request) {
    return this.getErrorDto(exception, request);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public BaseErrorResponseDto handleIllegalArgumentException(
      IllegalArgumentException exception, WebRequest request) {
    return this.getErrorDto(exception, request);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseErrorResponseDto handleAnyOtherException(Exception exception, WebRequest request) {
    return this.getErrorDto(exception, request);
  }
}
