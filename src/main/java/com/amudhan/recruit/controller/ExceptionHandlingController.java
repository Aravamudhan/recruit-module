package com.amudhan.recruit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;

/**
 * This class handles all the exceptions arising out of the application From here exceptions can be
 * forwarded to the external logging/error recording solutions
 * 
 * @author amudhan
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {


  @ExceptionHandler(value = {JobApplicationException.class})
  public ResponseEntity<Object> handleJobApplicationException(JobApplicationException e) {
    return handleError(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
    return handleError(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleGlobalException(Exception e) {
    return handleError(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  private ResponseEntity<Object> handleError(Exception e, HttpStatus status) {
    return new ResponseEntity<Object>(e.getMessage(), status);
  }
}
