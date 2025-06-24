package com.pkmtourney.tournament_manager;

import com.pkmtourney.tournament_manager.controller.PlayerController;
import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

public class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    void testGetAllPlayers() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(List.of(new Player(1, "Player 1", null, null, 0)));

        mockMvc.perform(get("/players"))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterPlayer() throws Exception {
        Player player = new Player(1, "Player 1", null, null, 0);
        when(playerService.registerPlayer(player)).thenReturn(player);

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Player 1\"}"))
                .andExpect(status().isOk());
    }
}
