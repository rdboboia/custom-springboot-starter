package es.rdboboia.custom.starter.api.error;

import es.rdboboia.custom.starter.api.error.dto.ResponseErrorDto;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/** Controller error handler. */
@RestControllerAdvice
public class ErrorHandler {

  private ResponseErrorDto getResponseErrorDto(Exception exception) {
    return ResponseErrorDto.builder()
        .timestamp(ZonedDateTime.now())
        .message(exception.getMessage())
        .details(exception.getStackTrace()[1].toString())
        .build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseErrorDto handleException(Exception exception, WebRequest request) {
    return this.getResponseErrorDto(exception);
  }
}
