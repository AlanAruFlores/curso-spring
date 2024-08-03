package org.mokito.example.persistence.entity.repository;

import org.mokito.example.persistence.entity.Player;

import java.util.List;
import java.util.stream.Collectors;
/*
* Nota : vamos a simular si tuviesemos contacto con una BD
* */

public class PlayerRepositoryImpl implements IPlayerRepository{

    private List<Player> players = List.of(
            new Player(1L, "Lionel Messi", "Paris Saint-Germain", "Forward"),
            new Player(2L, "Cristiano Ronaldo", "Al Nassr", "Forward"),
            new Player(3L, "Kylian Mbappé", "Paris Saint-Germain", "Forward"),
            new Player(4L, "Kevin De Bruyne", "Manchester City", "Midfielder"),
            new Player(5L, "Virgil van Dijk", "Liverpool", "Defender"),
            new Player(6L, "Manuel Neuer", "Bayern Munich", "Goalkeeper")
    );

    @Override
    public List<Player> findAll() {
        return this.players;
    }

    @Override
    public Player findById(Long id) {
        return this.players.stream().filter(player -> player.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public void save(Player player) {
        this.players.add(player);
    }

    @Override
    public void deleteById(Long id) {
        this.players = this.players.stream().filter(player -> player.getId() != id).collect(Collectors.toList());
    }
}
