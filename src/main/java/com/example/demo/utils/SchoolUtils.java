package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SchoolUtils {
    private SchoolUtils()
    {

    }
    public static ResponseEntity<String> getResponseEntity(String respondMessage, HttpStatus httpStatus)
    {
        return new ResponseEntity<String>("{\"message\":\"" + respondMessage +  "\"}", httpStatus);
    }
}
