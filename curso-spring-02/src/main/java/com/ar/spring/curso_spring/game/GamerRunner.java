package com.ar.spring.curso_spring.game;

import org.springframework.stereotype.Component;

@Component //Indico que sera un componente a inyectar
public class GamerRunner {
    GameConsole game;

    //Como no se usa @Qualifier, toma como preferencia el GameConsole con la anottation @Primary
    public GamerRunner(GameConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("\nEjecutando Juego: "+game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
