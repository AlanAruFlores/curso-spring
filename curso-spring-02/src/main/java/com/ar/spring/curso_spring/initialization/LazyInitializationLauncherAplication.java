package com.ar.spring.curso_spring.initialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/*
* Cuando creamos los beans y components que va a requerir el arranque de nuestra aplicacion. Por defecto spring establece
* estos beans como en modo "Eager", es decir, que se inicilizaran antes del arranque de la app
*
* Mientras que @Lazy indica que el bean se carga en el momento que se llama a la misma.
*
* El modo por defecto es @Eager y es la mas recomendable pero a su vez puede llegar a consumimr mucha mas memoria
* a diferencia de @Lazy.
* */
@Configuration
@ComponentScan
public class LazyInitializationLauncherAplication {

    @Component
    public class ClassA{
        public ClassA(){
            System.out.println("ClassA Bean Cargado");
        }
    }

    @Component
    @Lazy
    public class ClassB{
        private ClassA classA;

        public ClassB(ClassA classA){
            System.out.println("ClassB Bean Cargado");
            this.classA = classA;
        }
    }

    public static void main(String[] args) {
        try(var contextoSpring = new AnnotationConfigApplicationContext(LazyInitializationLauncherAplication.class);){
//            ClassB classB = contextoSpring.getBean(ClassB.class);
            Arrays.stream(contextoSpring.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println("\nBEANS CARGADOS CORRECTAMENTE\n");

            System.out.println(contextoSpring.getBean(ClassB.class));
        }
    }
}
