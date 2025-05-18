package com.ttknpdev.mycommonsresponse.exception.handler;

public class NotAllowed extends RuntimeException{
    private String currentCourse;
    public NotAllowed(String message) {
        super(message);
        currentCourse = message;
    }

    public String getCurrentCourse() {
        return currentCourse;
    }
}
