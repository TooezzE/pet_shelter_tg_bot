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
import pet.shelter.model.Cat;
import pet.shelter.model.Dog;
import pet.shelter.repository.CatRepository;
import pet.shelter.repository.DogRepository;
import pet.shelter.services.CatService;
import pet.shelter.services.DogService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
public class DogControllerTest {
    @MockBean
    private DogRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private DogService service;

    @Test
    void create() throws Exception {

        Dog dog = new Dog();
        dog.setName("Рекс");
        JSONObject object = new JSONObject();
        object.put("name", "Рекс");
        Mockito.when(service.create(dog)).thenReturn(dog);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/dog")
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Рекс"));


    }

    @Test

    void update() throws Exception {
        final String name = "Рекс";
        final String breed = "Дворняга";
        final int birthday = 2000;
        final String descriptionOfThePet = "Мыть";
        final long id = 1;

        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("breed", breed);
        object.put("birthday", birthday);
        object.put("descriptionOfThePet", descriptionOfThePet);
        object.put("id",id);
        Dog dog = new Dog(id,name,breed,birthday,descriptionOfThePet);
        dog.setName(name);
        when(service.update(any(Long.class),any(Dog.class))).thenReturn(dog);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(dog));
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/dog/"+dog.getId())
                                .content(objectMapper.writeValueAsString(dog))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.name").value("Рекс")));

    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/dog/{id}", 1))
                .andExpect(status().isOk());
        verify(service).delete(1L);
    }

    @Test
    void getAll() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Dog(1L,"Имя","Порода",1234,"Особенности"),
                new Dog(2L,"Имя","Порода",1234,"Особенности")

        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/dog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }
    @Test
    void getById() throws Exception {
        final String name = "Рекс";
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
        Dog dog = new Dog(id, name, breed, birthday, descriptionOfThePet);
        dog.setName(name);
        when(service.findById(any(Long.class))).thenReturn(dog);
        mockMvc.perform(get("/dog/" + dog.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
}















