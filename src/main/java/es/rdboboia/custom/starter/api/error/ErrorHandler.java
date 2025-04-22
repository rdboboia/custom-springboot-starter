package es.rdboboia.custom.starter.api.error;

import es.rdboboia.custom.starter.api.error.dto.BaseErrorResponseDto;
import es.rdboboia.custom.starter.api.error.dto.ValidationErrorResponseDto;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/** REST controller error handler. */
@RestControllerAdvice
public class ErrorHandler {

  /* *********************** */
  /* PRIVATE UTILITY METHODS */
  /* *********************** */

  private BaseErrorResponseDto getErrorDto(Exception exception) {
    return BaseErrorResponseDto.builder()
        .timestamp(ZonedDateTime.now())
        .message(exception.getMessage())
        .details(exception.getStackTrace()[1].toString())
        .build();
  }

  private ValidationErrorResponseDto getValidationErrorDto(
      Exception exception, List<String> errors) {

    ValidationErrorResponseDto errorDto = (ValidationErrorResponseDto) this.getErrorDto(exception);
    errorDto.setErrors(errors);

    return errorDto;
  }

  /* ****************** */
  /* EXCEPTION HANDLERS */
  /* ****************** */

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseErrorResponseDto handleNoSuchElementException(
      Exception exception, WebRequest request) {
    return this.getErrorDto(exception);
  }

  /**
   * Handle {@link MethodArgumentNotValidException} properly returning the fields with validation
   * errors.
   *
   * @param exception {@link MethodArgumentNotValidException}.
   * @param request {@link WebRequest} request.
   * @return response {@link BaseErrorResponseDto}.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseErrorResponseDto handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception, WebRequest request) {

    List<String> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + " " + e.getDefaultMessage())
            .toList();

    return this.getValidationErrorDto(exception, errors);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseErrorResponseDto handleException(Exception exception, WebRequest request) {
    return this.getErrorDto(exception);
  }
}
