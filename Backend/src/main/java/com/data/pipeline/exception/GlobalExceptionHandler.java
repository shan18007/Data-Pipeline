package com.data.pipeline.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.data.pipeline.response.ErrorResponse;
import com.data.pipeline.service.DataPipelineService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	DataPipelineService dataPipelineService;
	
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
    	log.error("ResourceNotFoundException: ");
    	
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), "Data not present in the Database");
    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}    


