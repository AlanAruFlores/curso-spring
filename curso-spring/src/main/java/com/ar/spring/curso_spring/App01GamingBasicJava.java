package com.ar.spring.curso_spring;

import com.ar.spring.curso_spring.game.GamerRunner;
import com.ar.spring.curso_spring.game.MarioGame;
import com.ar.spring.curso_spring.game.PacmanGame;
import com.ar.spring.curso_spring.game.StreetFighterGame;

public class App01GamingBasicJava {

    public static void main(String[] args){
        MarioGame mario = new MarioGame();
        StreetFighterGame streetFighter = new StreetFighterGame();
        PacmanGame pacman = new PacmanGame();

        GamerRunner gameRunner = new GamerRunner(streetFighter);
        GamerRunner gameRunner2 = new GamerRunner(mario);
        GamerRunner gameRunner3 = new GamerRunner(pacman);

        gameRunner.run();
        gameRunner2.run();
        gameRunner3.run();
    }
}
