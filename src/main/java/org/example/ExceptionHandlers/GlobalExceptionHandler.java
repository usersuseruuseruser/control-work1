package org.example.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultErrorHandler(Exception e) {
        ModelAndView modelAndView = new ModelAndView("Exception");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
