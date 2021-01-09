package com.cemayan.annotation.aop;

import java.lang.annotation.*;

@Target(value = { ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogExecutionTime {

}