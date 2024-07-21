package com.ar.spring.curso_spring.dependency.inyection;

/*
*  Vamos a ver las diversas formas de inyectar dependencias dentro de nuestro proyecto
* */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/*
* SE PUEDE IMPLEMENTAR LA INYECCION DE DEPENDENCIAS DE 3 MANERAS
*
* 1.ATRIBUTO
* 2.SETTER
* 3.CONSTRUCTOR
* */


@Configuration
@ComponentScan //Si no indico, busca los componentes en el paquete actual
public class DependencyInjectionLauncherApplication {

    @Component
    public class ComponentePadre{
        //@Autowired ---> Inyeccion de dependencia por campo (ATRIBUTO)
        DependenciaUno d1;

        //@Autowired --> Inyeccion de dependencia por campo (ATRIBUTO)
        DependenciaDos d2;

        //@Autowired NO ES OBLIGATORIO (METOOD MAS RECOMENDADO) (CONSTRUCTOR)
        public ComponentePadre(DependenciaUno d1, DependenciaDos d2){
            System.out.println("INYECCION DE DEPENDENCIAS POR CONSTRUCTOR");
            this.d1 = d1;
            this.d2 = d2;
        }

        /*
        @Autowired
        public void setDependenciaUno(DependenciaUno d1){ (SETTER)
            System.out.println("INYECTANDO DESDE EL METODO SETTER");
            this.d1 = d1;
        }

        @Autowired
        public void setDependenciaDos(DependenciaDos d2){ (SETTER)
            System.out.println("INYECTANDO DESDE EL METODO SETTER");
            this.d2 = d2;
        }

        public String toString(){
            return "DEPENDENCIA D1: "+ d1 +" | DEPENDENCIA D2: "+ d2;
        }*/
    }

    @Component
    public class DependenciaUno{
    }

    @Component
    public class DependenciaDos{
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(DependencyInjectionLauncherApplication.class)) {
            System.out.println("Spring Boot Application Context");

            //Mostramos los beans definidos
            //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(ComponentePadre.class));

        }
    }
}
