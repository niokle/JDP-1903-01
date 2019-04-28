package com.kodilla.ecommercee.error.configuration;

import com.kodilla.ecommercee.error.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private ErrorDto errorDto = new ErrorDto();

    public Errors(HttpServletRequest httpServletRequest) {
        String errorCode = httpServletRequest.getAttribute("javax.servlet.error.status_code").toString();
        String errorMessage = httpServletRequest.getAttribute("javax.servlet.error.message").toString();
        switch (errorCode) {
            case "400":
                errorDto.setCode(errorCode);
                errorDto.setMessage(errorMessage);
                break;
            case "404":
                errorDto.setCode(errorCode);
                errorDto.setMessage("endpoint not exist");
                break;
            default:
                errorDto.setCode(errorCode);
                errorDto.setMessage("other error");
        }
    }
}
