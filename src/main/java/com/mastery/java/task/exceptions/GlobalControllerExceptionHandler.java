package com.mastery.java.task.exceptions;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.sql.SQLException;

import static com.mastery.java.task.Application.LOGGER;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                               final HttpHeaders headers,
                                                               final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Http message not readable exception";
        LOGGER.log(Level.SEVERE, "400 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                               final HttpHeaders headers,
                                                               final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Method argument not valid exception";
        LOGGER.log(Level.SEVERE, "400 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "Constraint violation exception";
        LOGGER.log(Level.SEVERE, "400 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "Data integrity violation exception";
        LOGGER.log(Level.SEVERE, "400 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "Employee not found exception";
        LOGGER.log(Level.SEVERE, "404 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<Object> handleMethodNotAllowedException(final MethodNotAllowedException ex, final WebRequest request) {
        final String bodyOfResponse = "Method not allowed Exception";
        LOGGER.log(Level.SEVERE, "405 Status Code: ", bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(final SQLException ex, final HttpServletRequest request) {
        final String bodyOfResponse = "Problem with DataBase";
        LOGGER.info("SQLException Occurred :: URL=" + request.getRequestURL());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, (WebRequest) request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "Data access exception";
        LOGGER.log(Level.SEVERE, "409 Status Code: ", ex);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternalServerError(final Exception ex, final WebRequest request) {
        final String bodyOfResponse = "Internal server error";
        LOGGER.log(Level.SEVERE, "500 Status Code: Internal server error", ex);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
