package eu.kiminiuslt.bdsm.advice;

import eu.kiminiuslt.bdsm.commons.dto.ApiExceptionResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(FileSizeLimitExceededException.class)
  @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
  public ApiExceptionResponse handleFileToLargeException(FileSizeLimitExceededException ex) {
    return ApiExceptionResponse.builder()
        .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
        .message(ex.getMessage())
        .build();
  }
}
