package uk.gov.ons.fwmt.gateway.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
  public ResponseEntity<GatewayCommonErrorDTO> makeCommonError(HttpServletRequest request, Throwable exception,
      HttpStatus status, String error, String message) {
    GatewayCommonErrorDTO errorDTO = new GatewayCommonErrorDTO();
    errorDTO.setError(error);
    errorDTO.setException(exception.getClass().getName());
    errorDTO.setMessage(message);
    errorDTO.setPath(request.getContextPath());
    errorDTO.setStatus(status.value());
    errorDTO.setTimestamp(LocalTime.now().toString());
    return new ResponseEntity<>(errorDTO, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GatewayCommonErrorDTO> handleAnyException(HttpServletRequest request, Exception exception) {
    exception.printStackTrace();
    return makeCommonError(request, exception, HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", "Unknown error");
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<GatewayCommonErrorDTO> handleContentTypeException(HttpServletRequest request,
      HttpMediaTypeNotSupportedException exception) {
    return makeCommonError(request, exception, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Invalid content type",
        exception.getMessage());
  }

  @ExceptionHandler(InvalidFileNameException.class)
  public ResponseEntity<GatewayCommonErrorDTO> handleInvalidFileNameException(HttpServletRequest request,
      InvalidFileNameException exception) {
    return makeCommonError(request, exception, HttpStatus.BAD_REQUEST, "Invalid CSV File Name", exception.toString());
  }

}
