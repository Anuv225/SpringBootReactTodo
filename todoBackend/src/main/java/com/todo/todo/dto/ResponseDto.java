package com.todo.todo.dto;

public class ResponseDto {

    public String message;

    public ResponseDto() {
    }

    public ResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
