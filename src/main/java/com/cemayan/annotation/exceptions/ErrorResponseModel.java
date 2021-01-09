package com.cemayan.annotation.exceptions;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponseModel {

    private String timestamp;
    private Integer status;
    private String error;
    private List<ErrorMessage> message;

    private String msg;

    public ErrorResponseModel() {
    }

    public ErrorResponseModel(String timestamp, Integer status, List<ErrorMessage> message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ErrorMessage> getMessage() {
        return message;
    }

    public void setMessage(List<ErrorMessage> message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
