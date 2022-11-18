package com.wallet.wallet.handler.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.wallet.wallet.handler.ErrorDetails;
import com.wallet.wallet.handler.exeption.ExampleException;

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class,
            ExampleException.class
    })
    @ResponseBody
    public ResponseEntity<Object> notFoundHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(NOT_FOUND, request.getRequestURI(), new ErrorDetails(exception));
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler({ Forbidden.class })
    @ResponseBody
    public ResponseEntity<Object> forbiddenHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(FORBIDDEN, request.getRequestURI(), new ErrorDetails(exception));
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler({ Unauthorized.class })
    @ResponseBody
    public ResponseEntity<Object> unauthorizedHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(FORBIDDEN, request.getRequestURI(), new ErrorDetails(exception));
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({})
    @ResponseBody
    public ResponseEntity<Object> alreadyExistsHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(CONFLICT, request.getRequestURI(), new ErrorDetails(exception));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ ArithmeticException.class,
            MissingRequestHeaderException.class,
            MethodArgumentNotValidException.class,
            NullPointerException.class,
            IllegalArgumentException.class,
            IndexOutOfBoundsException.class,
    })
    @ResponseBody
    public ResponseEntity<Object> badRequestHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(BAD_REQUEST, request.getRequestURI(), new ErrorDetails(exception));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<Object> internalErrorHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(INTERNAL_SERVER_ERROR, request.getRequestURI(), new ErrorDetails(exception));
    }
}
