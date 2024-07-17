package com.ar.spring.curso_spring.game;

public class GamerRunner {
    GameConsole game;

    public GamerRunner(GameConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Ejecutando Juego: "+game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
