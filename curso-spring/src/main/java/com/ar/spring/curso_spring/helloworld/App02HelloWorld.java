package com.ar.spring.curso_spring.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorld{

    public static void main(String[] args) {
        //Lo que vamos a hacer ahora es hacer uso de inyeccion de dependencias.
        //Para el mismo necesitamos el spring context

        //1. Lanzar spring context
        try(AnnotationConfigApplicationContext contextoSpring =
                    new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){

            //2. Configurar los objetos para que spring pueda administrarlos - @Configuration para lanzar el contexto de spring
            System.out.println(contextoSpring.getBean("getService"));
            System.out.println("Hola: "+contextoSpring.getBean("getName"));
            System.out.println(contextoSpring.getBean("getPersona"));
            System.out.println(contextoSpring.getBean("direccion"));
            System.out.println(contextoSpring.getBean(Direccion.class));
            //System.out.println(contextoSpring.getBean(Persona.class));

            System.out.println(contextoSpring.getBean("getPersonaDosPorLlamadaDeMetodos"));
            System.out.println(contextoSpring.getBean("getPersonPorParametros"));

            //Obtener todos los beans
            System.out.println("LISTA DE BEANS: ");
            String[] nombresBeans = contextoSpring.getBeanDefinitionNames();
            Arrays.stream(nombresBeans).forEach(System.out::println);

            System.out.println("CANTIDAD DE BEANS: "+contextoSpring.getBeanDefinitionCount());

            System.out.println(contextoSpring.getBean("getPersonaQualifier"));

        }

    }
}
