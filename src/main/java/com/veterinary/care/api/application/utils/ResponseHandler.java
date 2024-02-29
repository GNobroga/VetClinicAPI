package com.veterinary.care.api.application.utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ResponseHandler {
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ResponseBody {
        private String title = "An error occurred";
        private int status = 500;
        private List<String> details;
    }

    public static ResponseBody isSuccess(String title, List<String> details) {
        return new ResponseBody(title, 200, details);
    }

    public static ResponseBody isFailure(String title, List<String> details) {
        return new ResponseBody(title, 400, details);
    }

}
