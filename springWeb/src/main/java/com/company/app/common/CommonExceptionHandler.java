package com.company.app.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handlException(Model model, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("common/error");
		return mv;
	}

}
