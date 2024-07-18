package com.ar.security.spring_security.recursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecursoTodo {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<Todo> LISTA_TODO =  List.of(new Todo("Alan", "Descripcion01"),
            new Todo("Alex","Descripcion02"));

    @GetMapping("/todos")
    public List<Todo> devolverTodos(){
        return LISTA_TODO;
    }

    @GetMapping("/usuario/{username}/todos")
    public Todo devolverTodoPorNombreUsuario(@PathVariable("username") String username){
        return LISTA_TODO.get(0);
    }

    @PostMapping("/usuario/{username}/todos")
    public void ingresarNuevoTodo(@PathVariable String username, @RequestBody Todo todo){
        logger.info("El usuario {} creo el todo : {}", username, todo);
    }

}

record Todo(String usuario, String descripcion){}
