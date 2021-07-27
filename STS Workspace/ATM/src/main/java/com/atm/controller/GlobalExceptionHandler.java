package com.atm.controller;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.atm.exception.DenominationNotAvailableException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, reason = "Incorrect amount requested")
    @ExceptionHandler(DenominationNotAvailableException.class)
    public ResponseEntity<Object> handleRangeNotSatisfiableException() {
        logger.error("DenominationNotAvailableException handler executed. Returning 416 error code");
        return new ResponseEntity<Object>( "Incorrect amount requested",HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }
    
    @ExceptionHandler(value = { Unauthorized.class }) 
        public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
  
    	logger.error("Unauthorized Exception: ",ex.getMessage());
   
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
   
        }
}
