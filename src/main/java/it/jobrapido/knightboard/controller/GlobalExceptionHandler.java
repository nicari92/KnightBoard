package it.jobrapido.knightboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.jobrapido.knightboard.interfaces.service.ConsolePrinterService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @Autowired
    private ConsolePrinterService consolePrinterService;

    @ExceptionHandler(TypeMismatchException.class)
    void handleTypeMismatchException(TypeMismatchException ex) throws JsonProcessingException {
        consolePrinterService.printGenericErrorResultAsJson();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    void handleWebExchangeBindException(WebExchangeBindException ex) throws JsonProcessingException {
        consolePrinterService.printGenericErrorResultAsJson();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationExceptions(MethodArgumentNotValidException ex) throws JsonProcessingException {
        consolePrinterService.printGenericErrorResultAsJson();
    }
}
