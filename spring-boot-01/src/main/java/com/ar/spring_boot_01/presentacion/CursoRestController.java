package com.ar.spring_boot_01.presentacion;

import com.ar.spring_boot_01.dominio.Curso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CursoRestController {

    @RequestMapping("/cursos")
    public List<Curso> retrieveAllCourses(){
        return Arrays.asList(
                new Curso(1L,"Curso1","Alan"),
                new Curso(2L,"Curso1","Alan"),
                new Curso(3L,"Curso1","Alan"),
                new Curso(4L,"Curso1","Alan")
                );
    }
}
