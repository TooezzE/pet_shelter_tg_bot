package pet.shelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver;
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
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatRepository;
import pet.shelter.services.CatService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void update() throws Exception {
        final String name = "Мурзик";
        final String breed = "Дворняга";
        final int birthday = 2000;
        final String descriptionOfThePet = "Мыть";
        final long id = 1;

        JSONObject catObject = new JSONObject();
        catObject.put("name", name);
        catObject.put("breed", breed);
        catObject.put("birthday", birthday);
        catObject.put("descriptionOfThePet", descriptionOfThePet);
        catObject.put("id", id);
        Cat cat = new Cat(id, name, breed, birthday, descriptionOfThePet);
        cat.setName(name);
        when(service.update(any(Long.class), any(Cat.class))).thenReturn(cat);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(cat));
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/cat/" + cat.getId())
                                .content(objectMapper.writeValueAsString(cat))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.name").value("Мурзик")));

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
                new Cat(1L, "Имя", "Порода", 1234, "Особенности"),
                new Cat(2L, "Имя", "Порода", 1234, "Особенности")

        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }

    @Test
    void getById() throws Exception {
        final String name = "Мурзик";
        final String breed = "Дворняга";
        final int birthday = 2000;
        final String descriptionOfThePet = "Мыть";
        final long id = 1;

        JSONObject catObject = new JSONObject();
        catObject.put("name", name);
        catObject.put("breed", breed);
        catObject.put("birthday", birthday);
        catObject.put("descriptionOfThePet", descriptionOfThePet);
        catObject.put("id", id);
        Cat cat = new Cat(id, name, breed, birthday, descriptionOfThePet);
        cat.setName(name);
        when(service.findById(any(Long.class))).thenReturn(cat);
        mockMvc.perform(get("/cat/" + cat.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
    }







