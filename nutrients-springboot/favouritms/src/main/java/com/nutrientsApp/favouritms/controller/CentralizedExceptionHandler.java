package com.nutrientsApp.favouritms.controller;


import com.nutrientsApp.favouritms.exceptions.FavoritedItemExistException;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	private static final Logger log =LoggerFactory.getLogger(CentralizedExceptionHandler.class);


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodNotFoundException.class)
    public String handleProductNotFound(FoodNotFoundException e) {
        String msg = e.getMessage();
        log.info("Handle Product Not Found",e);
        return msg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            FavoritedItemExistException.class
    })
    public String handleInvalid(Exception e) {
        String msg = e.getMessage();
        log.info("Handle invalid",e);
        return msg;
    }

}
