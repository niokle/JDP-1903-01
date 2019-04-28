package com.kodilla.ecommercee.error.controller;

import com.kodilla.ecommercee.error.configuration.Errors;
import com.kodilla.ecommercee.error.dto.ErrorDto;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ErrorDto handleError(HttpServletRequest httpServletRequest) {
        return new Errors(httpServletRequest).getErrorDto();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
