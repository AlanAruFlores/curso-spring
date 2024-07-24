package com.ar.spring.curso_spring.xmlconfig;

import com.ar.spring.curso_spring.game.GamerRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class XmlConfigApplication {

    public static void main(String[] args) {
        try(var contexto = new ClassPathXmlApplicationContext("configXmlApplication.xml")) {
            Arrays.stream(contexto.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(contexto.getBean("nombre"));
            System.out.println(contexto.getBean("edad"));
            contexto.getBean(GamerRunner.class).run();
        }
    }
}
