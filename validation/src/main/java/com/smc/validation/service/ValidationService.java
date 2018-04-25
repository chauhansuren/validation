package com.smc.validation.service
;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.validation.*;
@Service
@Slf4j

public class ValidationService {
    
	   static boolean result = false ; 
	    private String errorMsg = "repeated Sequence not allowed in password";
	    private static Pattern pattern = null;
	    Logger log  ;
	 //   static StringBuilder iseq = new StringBuilder();
	  //  static StringBuilder nseq = new StringBuilder();
	    private static final String PASSWORD_PATTERN = 
	              "((?=.*\\d)(?=.*[a-zA-Z]).(?=.*[0-9]).{5,12})";
	    
	    
	    String iseq;
	    String nseq;
	    
	    static int indexPointer = 0;
	    static int offset = 2;
	    Matcher matcher;
	    
	    public boolean checkForNull(String password) {
	    	 int len = password.length();
	    if (password == null || len == 0)
	    	return false ;
	    else 
			   return true;
	    }
	   
	    public boolean checkForSize(String password) {
	    	 int len = password.length();
	    if ((len > 4) && (len < 13))
	    {
	    		return true ;
	    }
	    else 
			   return false;
	    }
	    public boolean checkForPattern(String password) {
	    	
	    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	    Matcher matcher = pattern.matcher(password) ;	    
	   if (matcher.find())
	   {
		   return true;
	   }
		   return false;
	    }
	    
	    public boolean checkForSequence(String password) {
		// TODO Auto-generated method stub
	    	int len = password.length();
		 System.out.println("password = " + password) ;
		 
		 for (int i=0; i<len/2; i++)
		 {
			 for (int j=0; j<=len-2*offset; j++)
			 {
				 iseq = password.substring(j,  offset+j);
				 nseq = password.substring(j+offset, j+offset+offset);
				if(iseq.equalsIgnoreCase(nseq))
				{
				return false ;		 
		 }
			
		 }
			 offset++;				 			
	}
		 offset=2;
		 return true;
	
	    }
}

