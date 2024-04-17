package com.lowleveldesign.crms.ErrorHandling;

import com.lowleveldesign.crms.Controllers.BuildingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundExceptions (
            UserNotFoundException ex) {
        logger.error("The User you're trying to fetch doesn't exist!");
        ErrorResponse errorResponse = new ErrorResponse( "USER DOESN'T EXIST", ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundExceptions (
            ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse( "RESOURCE DOESN'T EXIST", ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(GenericClientException.class)
    public ResponseEntity<ErrorResponse> handleGenericClientExceptions (
            GenericClientException ex) {
        ErrorResponse errorResponse = new ErrorResponse( "CLIENT ERROR", ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
