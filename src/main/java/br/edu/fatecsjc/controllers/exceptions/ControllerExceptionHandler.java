package br.edu.fatecsjc.controllers.exceptions;

import br.edu.fatecsjc.services.exceptions.AuthorizationException;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.FileException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Resource not found.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Data Integrity.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError error = new ValidationError(
                System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error.", e.getMessage(), request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors())
            error.addError(x.getField(), x.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Access denied.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileExceptionHandler(FileException e, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceExceptionHandler(AmazonServiceException e, HttpServletRequest request) {

        HttpStatus code = HttpStatus.valueOf(e.getErrorCode());

        StandardError error = new StandardError(
                System.currentTimeMillis(), code.value(), "Amazon Service error.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(code).body(error);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClientExceptionHandler(AmazonClientException e, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Amazon Client error.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3ExceptionHandler(AmazonS3Exception e, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Amazon S3 error.", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
