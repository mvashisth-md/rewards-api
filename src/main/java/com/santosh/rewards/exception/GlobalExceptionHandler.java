package com.santosh.rewards.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * 
 * Consolidates multiple, scattered Exceptions into a single, global error handling class.
 * @author Santosh Singh
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
	    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			  request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST); 
		}
		 

		if(body == null) {
			body = prepareRespBody(ex.getLocalizedMessage());
		}
		
		
		return new ResponseEntity<>(body, headers, status);
	}
	
    @ExceptionHandler(value= { TransactionNotFoundException.class })
    protected ResponseEntity<Object> handleTransactionNotFoundException( TransactionNotFoundException ex, WebRequest request) {
        
        return handleExceptionInternal(ex, prepareRespBody(ex.getLocalizedMessage()), 
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }  
    
    @ExceptionHandler(value= { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict( RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, prepareRespBody(ex.getLocalizedMessage()), 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    
    /**
     * We can have customized error response. For time being only setting message
     * @param msg
     * @return
     */
    private Map<String, String> prepareRespBody(String msg) {
    	
    	Map<String, String> bodyOfResponse = new HashMap<>();
    	bodyOfResponse.put("message", msg);
    	
    	return bodyOfResponse;
    }
    

}