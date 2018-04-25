package com.smc.validation.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smc.validation.service.ValidationService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j

public class PasswordController  {
	boolean result = true;
	String jresult ;
	Logger log ;
	ValidationService validationService  = new ValidationService() ; 
	
	ObjectMapper mapper = new ObjectMapper();
	@GetMapping("/")

	public String HelloWorld()
	{
		
		return "Hello World" ;
	}
@PostMapping(value="/password"
	/*	,produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE */
			)


	public String validatePassword(@RequestParam(value="password") String password) {
		//	result = validationService.getResult(password) ;
	System.out.println("password = " + password );
	result = validationService.checkForNull(password) && 
			validationService.checkForSize(password) &&  
			validationService.checkForPattern(password) &&
			validationService.checkForSequence(password) ;
			
//	 log.info("result = " + result );
			
	try {
		jresult = mapper.writeValueAsString("Password is " + result) ;
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	} 
	
	return jresult;
	}
}
