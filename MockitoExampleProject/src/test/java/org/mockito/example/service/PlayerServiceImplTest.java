package org.mockito.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.example.DataProvider;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mokito.example.persistence.entity.Player;
import org.mokito.example.persistence.entity.repository.PlayerRepositoryImpl;
import org.mokito.example.service.PlayerServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {
    @Mock
    PlayerRepositoryImpl playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(true);
    }

    @Test
    public void testFindAll(){
        //given
       // PlayerRepositoryImpl playerRepository = mock(PlayerRepositoryImpl.class);
        // PlayerServiceImpl playerService = new PlayerServiceImpl(playerRepository);
        //when
        when(playerRepository.findAll()).thenReturn(DataProvider.getAllPlayersMock());

        List<Player> result = playerService.findAll();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Cristiano Ronaldo", result.get(0).getName());
        assertEquals("Al Nassr", result.get(0).getTeam());
        assertEquals("Forward", result.get(0).getPosition());

    }


    @Test
    public void testFindById(){
        //given
        Long idPlayer = 1L;
        //when
        //Any long acepta cualquier valor long, y devolvera siempre el playerMock.get(0)
        when(playerRepository.findById(anyLong())).thenReturn(DataProvider.getAllPlayersMock().get(0));
        Player result = playerService.findById(idPlayer);

        //then
        assertNotNull(result);
        assertEquals("Cristiano Ronaldo", result.getName());

        //Testeamos que el metodo finById se llame 1 vez
        verify(playerRepository, times(1)).findById(anyLong());
    }


    @Test
    public void testSave(){
        //given
        Player newPlayer =  DataProvider.getNewPlayerMock();

        //when
        this.playerService.save(newPlayer);

        //then
        //Sirve para poder capturar el argumento que se manda al metodo
        ArgumentCaptor<Player> argumentCaptorPlayer = ArgumentCaptor.forClass(Player.class);
        verify(this.playerRepository).save(argumentCaptorPlayer.capture());

        //Del argumento (Player object) capturado, evaluamos sus propiedades
        assertEquals(10L, argumentCaptorPlayer.getValue().getId());
        assertEquals("Neymar", argumentCaptorPlayer.getValue().getName());
        assertEquals("Brasil", argumentCaptorPlayer.getValue().getTeam());

    }


    @Test
    public void testDeleteById(){
        //given
        Long idPlayerToDelete = 2L;

        //when
        this.playerService.deleteById(idPlayerToDelete);

        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository,times(1)).deleteById(argumentCaptor.capture());
        assertEquals(2L, argumentCaptor.getValue());
    }


}
