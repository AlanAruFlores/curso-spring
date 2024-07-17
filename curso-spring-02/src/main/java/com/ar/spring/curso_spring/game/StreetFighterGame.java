package com.ar.spring.curso_spring.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Qualifier("street")
public class StreetFighterGame implements GameConsole{

    public void up(){
        System.out.println("Saltar");
    }

    public void down(){
        System.out.println("Agachar");
    }

    public void left(){
        System.out.println("Volver");
    }

    public void right(){
        System.out.println("Caminar");
    }
}
