package org.mockito.example;

import org.mokito.example.persistence.entity.Player;

import java.util.List;

public class DataProvider {

    public static List<Player> getAllPlayersMock(){
        return List.of(
                new Player(1L, "Cristiano Ronaldo", "Al Nassr", "Forward"),
                new Player(2L, "Kevin De Bruyne", "Manchester City", "Midfielder"),
                new Player(3L, "Manuel Neuer", "Bayern Munich", "Goalkeeper")
        );
    }

    public static Player getNewPlayerMock(){
        return new Player(10L, "Neymar", "Brasil", "Forward");
    }
}
