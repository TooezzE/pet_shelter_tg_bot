package pet.shelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pet.shelter.controller.CatAdopterController;
import pet.shelter.controller.CatController;
import pet.shelter.model.Cat;
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatAdopterRepository;
import pet.shelter.repository.CatRepository;
import pet.shelter.services.CatAdopterService;
import pet.shelter.services.CatService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatAdopterController.class)
public class CatAdopterControllerTest {
    @MockBean
    private CatAdopterRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private CatAdopterService service;

    @Test
    void create() throws Exception {

        CatAdopter adopter = new CatAdopter();
        adopter.setName("Иван");
        JSONObject object = new JSONObject();
        object.put("name", "Иван");
        Mockito.when(service.createAdopter(adopter)).thenReturn(adopter);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/adopter-cat")
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Иван"));


    }


    @Test
//не проходит
    void update() throws Exception {
        CatAdopter adopter = new CatAdopter();
        adopter.setName("Иван");
        JSONObject object = new JSONObject();
        object.put("name", "Иван");
        when(service.updateAdopter(1L, adopter)).thenReturn(adopter);
        mockMvc.perform(
                        put("/adopter-cat/"+adopter.getId())
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).updateAdopter(1L,adopter);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/adopter-cat/{id}", 1))
                .andExpect(status().isOk());
        verify(service).deleteAdopter(1L);
    }


    @Test
    void getAll() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new CatAdopter()));
        mockMvc.perform(MockMvcRequestBuilders.get("/adopter-cat/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }
}


