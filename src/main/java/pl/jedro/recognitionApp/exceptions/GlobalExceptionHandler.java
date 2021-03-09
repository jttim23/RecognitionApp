package pl.jedro.recognitionApp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PathNotSpecifiedException.class)
    public ResponseEntity<Object> handlePathNotSpecifiedException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please check if path to file is specified");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadParametersException.class)
    public ResponseEntity<Object> handleBadParametersException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please check provided parameters");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please check if token file in path");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }


}