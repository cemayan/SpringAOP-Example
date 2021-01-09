package com.cemayan.annotation.aop;

import com.cemayan.annotation.exceptions.ErrorMessage;
import com.cemayan.annotation.exceptions.MyNullException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@Aspect
public class ProductNullGuardHandler {

    @Autowired
    ObjectMapper objectMapper;

    //@Before ProductNullGuard annotaion'ının koyulduğu metoddan önce çalışacağını belirtiyor.
    @Around("@annotation(com.cemayan.annotation.aop.ProductNullGuard)")
    public void checkNullValues(ProceedingJoinPoint joinPoint) throws Throwable {

            //Annotation'ın koyulduğu yerdeki metod'un imzasını verir.
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            ProductNullGuard annotation = method.getAnnotation(ProductNullGuard.class);

            //Annotation koyulan metoddaki argümanları alır.
            Object[] args = joinPoint.getArgs();
            //Annotaion içine verilen değeri alır.
            String[] fieldNames = annotation.fieldNames();

            if(args.length > 0 ) {
                Object incomingDTO = args[0];
                List<ErrorMessage> errorMessages =  processedArray(incomingDTO, fieldNames);
                if(!errorMessages.isEmpty()) {
                    throwNullException(errorMessages);
                }
            }
    }

    private List<ErrorMessage> processedArray(Object dto, String[] fieldNames) {
        PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(dto);
         return Arrays.stream(fieldNames)
                .filter(field -> propertyAccessor.getPropertyValue(field) == null)
                .map(errorField -> new ErrorMessage(errorField, errorField + " field can't be null!"))
                 .collect(Collectors.toList());
    }

    private void throwNullException(List<ErrorMessage> errorMessages) throws JsonProcessingException {
        String errors = objectMapper.writeValueAsString(errorMessages);
        throw  new MyNullException(HttpStatus.BAD_REQUEST, errors);
    }
}
