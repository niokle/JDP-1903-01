package com.kodilla.ecommercee.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        Class exceptionType = (Class) request.getAttribute("javax.servlet.error.exception_type");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

        JSONObject jsonObject = new JSONObject()
                .put("status code", statusCode)
                .put("exeption", exception == null ? "" : exception.getMessage())
                .put("exeption type", exceptionType == null ? "" : exceptionType)
                .put("exeption message", message == null ? "" : message)
                .put("request uri", requestUri == null ? "" : requestUri)
                .put("servlet name", servletName == null ? "" : servletName);

        return jsonObject.toString();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
