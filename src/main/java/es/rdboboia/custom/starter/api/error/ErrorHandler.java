package es.rdboboia.custom.starter.api.error;

import es.rdboboia.custom.starter.api.error.dto.ResponseErrorDto;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/** Controller error handler. */
@RestControllerAdvice
public class ErrorHandler {

  private ResponseErrorDto getResponseErrorDto(Exception exception, List<String> errors) {
    return ResponseErrorDto.builder()
        .timestamp(ZonedDateTime.now())
        .message(exception.getMessage())
        .details(exception.getStackTrace()[1].toString())
        .errors(errors)
        .build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseErrorDto handleNoSuchElementException(Exception exception, WebRequest request) {
    return this.getResponseErrorDto(exception, null);
  }

  /**
   * Handle {@link MethodArgumentNotValidException} properly returning the fields with validation
   * errors.
   *
   * @param exception {@link MethodArgumentNotValidException}.
   * @param request {@link WebRequest} request.
   * @return response {@link ResponseErrorDto}.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseErrorDto handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception, WebRequest request) {

    List<String> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + " " + e.getDefaultMessage())
            .toList();

    return this.getResponseErrorDto(exception, errors);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseErrorDto handleException(Exception exception, WebRequest request) {
    return this.getResponseErrorDto(exception, null);
  }
}
