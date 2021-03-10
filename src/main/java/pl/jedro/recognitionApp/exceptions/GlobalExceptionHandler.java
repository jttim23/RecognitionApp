package pl.jedro.recognitionApp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please correctness of tokens file");
        body.put("exception message", exception.getMessage());
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please provide correct parameters");

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NameIsEmptyException.class)
    public ResponseEntity<Object> handleNameIsEmptyException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please provide name");

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please check if file with tokens in path");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }


}