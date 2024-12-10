package com.security.learn_spring_security_base.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoResource {

    //Lista
    private static List<Todo> LIST_TODO = new ArrayList<>(){
        {
            add(new Todo("Usuario Alan", "Spring backend"));
            add(new Todo("Pepe", "NET BACKEND"));
        };
    };

    //Logger de esta clase
    private Logger logger = LoggerFactory.getLogger(getClass());



    //Obtener toda la lista
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodosList(){
        return ResponseEntity.ok(LIST_TODO);
    }

    //Obtener un todo por username
    @GetMapping("/todos/{username}")
    public ResponseEntity<Todo> getTodoByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(LIST_TODO.stream().filter(
                todo ->  todo.user.equalsIgnoreCase(username)
        ).findFirst().orElse(null));
    }

    @PostMapping("/todos/{username}/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void postTodo(@PathVariable("username") String username, @RequestBody Todo todo){

        LIST_TODO.add(todo);

        //{} sirve para mandar argumentos
        logger.info("Insert {} for {}",todo,username);
    }

    //registro (similar a objeto)
    record Todo(String user, String descripcion){}
}

