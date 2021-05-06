package com.bsahu.chotaurl.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author bsahu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {



	/*
	 * @ExceptionHandler({ UrlException.class, }) public ModelAndView
	 * handleMessageException(UrlException ex) { ModelAndView modelAndView = new
	 * ModelAndView(); modelAndView.setViewName("error");
	 * modelAndView.addObject("message", ex.getMessage()); return modelAndView; }
	 */
	
    @ExceptionHandler({ Exception.class, })
    public ModelAndView handleException(Exception ex) {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }


}
