package com.kodilla.ecommercee.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping("/error")
    public String handleError() {
        LOGGER.error("ERROR - BLAD");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
