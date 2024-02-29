package com.veterinary.care.api.application.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ResponseHandler<TResult> {
    
    @RequiredArgsConstructor
    @Getter
    public enum Status {
        OK("ok"),
        ERROR("error");
        private final String status;
    }

    private String status;

    private String code;

    private List<String> messages = new ArrayList<>();

    private TResult result;

    public ResponseHandler(TResult result) {
        status = Status.OK.getStatus();
        code = String.valueOf(HttpStatus.OK.value());
        this.result = result;
    }

    public ResponseHandler(Status status, HttpStatus httpStatus, TResult result) {
        setStatus(status);
        setCode(httpStatus);
        this.result = result;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public void setStatus(Status status) {
        this.status = status.getStatus();
    }

    public void setCode(HttpStatus httpStatus) {
        this.code = String.valueOf(httpStatus.value());
    }
   
}
