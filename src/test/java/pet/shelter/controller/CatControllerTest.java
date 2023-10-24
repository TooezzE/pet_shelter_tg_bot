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
import pet.shelter.controller.CatController;
import pet.shelter.model.Cat;
import pet.shelter.repository.CatRepository;
import pet.shelter.services.CatService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
public class CatControllerTest {
    @MockBean
    private CatRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private CatService service;

    @Test
    void create() throws Exception {

        Cat cat = new Cat();
        cat.setName("Мурзик");
        JSONObject object = new JSONObject();
        object.put("name", "Мурзик");
        Mockito.when(service.create(cat)).thenReturn(cat);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cat")
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Мурзик"));


    }

    @Test
//не проходит
    void update() throws Exception {
        final String name = "Мурзик";
        final long id = 1;
        JSONObject catObject = new JSONObject();
        catObject.put("name", name);
        Cat cat = new Cat();
        cat.setName(name);

        when(repository.save(any(Cat.class))).thenReturn(cat);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(cat));
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/cat/" + id)
                                .content(objectMapper.writeValueAsString(cat))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Мурзик"));

    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/cat/{id}", 1))
                .andExpect(status().isOk());
        verify(service).delete(1L);
    }

    @Test
    void getAll() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Cat("Кот", "Порода", 2000, "Описание"),
                new Cat("Кот", "Порода", 2000, "Описание")
        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }
}






