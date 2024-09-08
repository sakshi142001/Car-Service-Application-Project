package com.practice.csa.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.exception.CarNotFoundByIdException;

@RestController
public class ApplicationHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carNotFoundByIdException(CarNotFoundByIdException ex){
		ErrorStructure<String> es= new ErrorStructure<String>();
		es.setStatuscode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage(ex.getMessage());
		es.setData("Car with the given id is not present in the database!!");
		
		return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);
	}
	
	
}
