package com.todo.todo.controller;

import com.todo.todo.dto.ResponseDto;
import com.todo.todo.dto.ToDoDto;
import com.todo.todo.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    // Get all ToDo items
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDo() {
        List<ToDoDto> todos = toDoService.getAllToDo();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // Get a single ToDo item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> getToDoById(@PathVariable long id) {
        ToDoDto todo = toDoService.getToDo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    // Add a new ToDo item
    @PostMapping
    public ResponseEntity<ResponseDto> addToDo(@Valid @RequestBody ToDoDto toDoDto) {
        ResponseDto response = toDoService.addTodo(toDoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing ToDo item
    @PutMapping("/{id}")
    public ResponseEntity<ToDoDto> updateToDo(@PathVariable long id,@Valid @RequestBody ToDoDto toDoDto) {
        ToDoDto updatedTodo = toDoService.updateTask(id, toDoDto);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    // Delete a ToDo item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteToDo(@PathVariable long id) {
        ResponseDto response = toDoService.deleteTodo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}