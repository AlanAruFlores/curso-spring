package org.mokito.example;

import org.mokito.example.persistence.entity.repository.IPlayerRepository;
import org.mokito.example.persistence.entity.repository.PlayerRepositoryImpl;
import org.mokito.example.service.PlayerServiceImpl;

public class Main {
    public static void main(String[] args) {
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();
        PlayerServiceImpl playerService = new PlayerServiceImpl(playerRepository);

        System.out.println(playerService.findAll());
        System.out.println(playerService.findById(4L));
    }
}