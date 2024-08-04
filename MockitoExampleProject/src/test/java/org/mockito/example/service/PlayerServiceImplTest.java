package org.mockito.example.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mokito.example.persistence.entity.Player;
import org.mokito.example.persistence.entity.repository.PlayerRepositoryImpl;
import org.mokito.example.service.PlayerServiceImpl;

import java.util.List;

public class PlayerServiceImplTest {

    @Test
    public void testFindAll(){
        //given
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();
        PlayerServiceImpl playerService = new PlayerServiceImpl(playerRepository);
        //when
        List<Player> result = playerService.findAll();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Lionel Messi", result.get(0).getName());
        assertEquals("Paris Saint-Germain", result.get(0).getTeam());
        assertEquals("Forward", result.get(0).getPosition());

    }

}
