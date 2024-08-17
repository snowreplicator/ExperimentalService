package ru.experimentalservice.Controller;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ru.experimentalservice.Service.Exception.BaseException;
import ru.experimentalservice.ViewModel.ExceptionViewModel;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ExceptionViewModel> handleException(BaseException exception) {
        ExceptionViewModel exceptionViewModel = new ExceptionViewModel(exception.getClass().getSimpleName(), exception.getMessage());
        logger.error("ExperimentalService exception: {}", exceptionViewModel);
        logger.error("Stacktrace: {}", (Object) exception.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionViewModel);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionViewModel> handleException(Exception exception) {
        ExceptionViewModel exceptionViewModel = new ExceptionViewModel(exception.getClass().getSimpleName(), exception.getMessage());
        logger.error("Exception: {}", exceptionViewModel);
        logger.error("Stacktrace: {}", (Object) exception.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionViewModel);
    }

}