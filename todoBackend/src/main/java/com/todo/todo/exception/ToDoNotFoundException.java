package com.todo.todo.exception;

public class ToDoNotFoundException extends RuntimeException{

    public ToDoNotFoundException(String message){
        super(message);
    }

}
