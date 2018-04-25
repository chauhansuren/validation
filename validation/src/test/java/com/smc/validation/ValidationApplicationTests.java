package com.smc.validation;
/**
 * 
 * Created by Surendra Chauhan
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.smc.validation.controller.PasswordController;
import com.smc.validation.service.ValidationService;



import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ValidationApplicationTests {
/*
	@Test
	public void contextLoads() {
	}
	*/
	private MockMvc mockMvc ;
	ValidationService validationService = new ValidationService() ;


@Autowired
/*	private ValidationService validationService ;  */
private PasswordController passwordController; 
private WebApplicationContext wac ;

@Before

public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.standaloneSetup(passwordController).build() ; 
	/*
	  DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
      this.mockMvc = builder.build();
      */

}

	@Test
	public void checkGet() throws Exception
	{
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());	
	}

	@Test
	public void checkPost() throws Exception
	{ 
		mockMvc.perform(post("/password").param("password","Rotherwood624"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testCheckForNull()
	{	
		assertTrue(validationService.checkForNull("Rotherw624")) ; 
	}	
	@Test
	public void testCheckForSize()
	{	
		assertTrue(validationService.checkForSize("Rotherw624"));
		assertFalse(validationService.checkForSize("RotherwoodRother35")); 
		assertTrue(validationService.checkForSize("Rotherw"));
		assertFalse(validationService.checkForSize("a2"));
	}	
	@Test
	public void testCheckForPattern()
	{	
		assertTrue(validationService.checkForPattern("Rotherw624"));
		assertFalse(validationService.checkForPattern("Rotherw")); 
		assertFalse(validationService.checkForPattern("54644")); 
	}	
	@Test
	public void testCheckForSequence()
	{	
		assertTrue(validationService.checkForSequence("Rotherw624")) ; 
		assertFalse(validationService.checkForSequence("Rotherwerw624"));
		assertFalse(validationService.checkForSequence("Rotherwoodwood624"));
	}	
	
	
}

