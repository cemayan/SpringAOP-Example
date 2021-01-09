package com.cemayan.annotation.adviser;

import com.cemayan.annotation.exceptions.ErrorMessage;
import com.cemayan.annotation.exceptions.ErrorResponseModel;
import com.cemayan.annotation.exceptions.MyNullException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class MyControllerAdviser {


    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler({MyNullException.class})
    public ResponseEntity<Object> handleException(MyNullException ex, WebRequest request) {

        ErrorResponseModel errorResponseModel = new ErrorResponseModel();

        try {
            List<ErrorMessage> errorMessages = objectMapper.readValue(ex.getReason(), new TypeReference<List<ErrorMessage>>() {
            });

            errorResponseModel = new ErrorResponseModel(
                    LocalDateTime.now().toString(),
                    HttpStatus.BAD_REQUEST.value(),
                    errorMessages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseModel);
    }
}
