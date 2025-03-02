package com.todo.todo.service;

import com.todo.todo.dto.ResponseDto;
import com.todo.todo.dto.ToDoDto;
import com.todo.todo.entity.ToDo;
import com.todo.todo.exception.ToDoNotFoundException;
import com.todo.todo.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

//     Get all ToDo items
    public List<ToDoDto> getAllToDo() {
        List<ToDo> allToDo = toDoRepository.findAll();
        List<ToDoDto> finalList = new ArrayList<>();

        for (ToDo todoEntity : allToDo) {
            finalList.add(convertToDto(todoEntity));
        }
        return finalList;
    }

    // Get a single ToDo item by ID
    public ToDoDto getToDo(long id)  {
        Optional<ToDo> todoEntity = toDoRepository.findById(id);
        if (todoEntity.isPresent()) {
            return convertToDto(todoEntity.get());
        } else {
            throw new ToDoNotFoundException("ToDo not found with id: " + id);
        }
    }

    // Delete a ToDo item by ID
    public ResponseDto deleteTodo(long id) {
        Optional<ToDo> todoEntity = toDoRepository.findById(id);
        if (todoEntity.isPresent()) {
            toDoRepository.delete(todoEntity.get());
            return new ResponseDto("ToDo deleted successfully");
        } else {
            throw new ToDoNotFoundException("ToDo not found with id: " + id);
        }
    }

    // Add a new ToDo item
    public ResponseDto addTodo(ToDoDto toDoDto) {
        ToDo toDo = convertToEntity(toDoDto);
        toDoRepository.save(toDo);
        return new ResponseDto("ToDo added successfully");
    }

    // Update an existing ToDo item
    public ToDoDto updateTask(long id, ToDoDto toDoDto) {
        Optional<ToDo> toDoEntity = toDoRepository.findById(id);
        if (toDoEntity.isPresent()) {
            ToDo toDo = toDoEntity.get();
            BeanUtils.copyProperties(toDoDto, toDo);
            toDo.setId(id); // Ensure the ID is not overwritten
            toDoRepository.save(toDo);
            return convertToDto(toDo);
        } else {
            throw new ToDoNotFoundException("ToDo not found with id: " + id);
        }
    }

    // Helper method to convert ToDo entity to ToDoDto
    private ToDoDto convertToDto(ToDo todoEntity) {
        ToDoDto todoDto = new ToDoDto();
        BeanUtils.copyProperties(todoEntity, todoDto);
        return todoDto;
    }

    // Helper method to convert ToDoDto to ToDo entity
    private ToDo convertToEntity(ToDoDto todoDto) {
        ToDo toDo = new ToDo();
        BeanUtils.copyProperties(todoDto, toDo);
        return toDo;
    }
}