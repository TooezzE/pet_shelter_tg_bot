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
import pet.shelter.model.Dog;
import pet.shelter.model.DogAdopter;
import pet.shelter.repository.DogAdopterRepository;
import pet.shelter.services.DogAdopterService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogAdopterController.class)
public class DogAdopterControllerTest {
    @MockBean
    private DogAdopterRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private DogAdopterService service;

    @Test
    void create() throws Exception {

        DogAdopter adopter = new DogAdopter();
        adopter.setName("Иван");
        JSONObject object = new JSONObject();
        object.put("name", "Иван");
        Mockito.when(service.createAdopter(adopter)).thenReturn(adopter);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/adopter-dog")
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Иван"));


    }


    @Test

    void update() throws Exception {
        final String name = "Имя";
        final int birthday = 2000;
        final String phoneNumber = "55-55-55";
        final String email = "aaa@mail.ru";
        final String address = "Rostov";
        final long chatId = 123;
        final long id=1;

        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("birthday", birthday);
        object.put("phoneNumber", phoneNumber);
        object.put("email", email);
        object.put("address", address);
        object.put("chatId", chatId);
        object.put("id", id);
        DogAdopter adopter = new DogAdopter(id,name,birthday,phoneNumber,email,address,chatId,new Dog());
        adopter.setName(name);
        when(service.updateAdopter(any(Long.class), any(DogAdopter.class))).thenReturn(adopter);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(adopter));
        mockMvc.perform(
                        put("/adopter-dog/" + adopter.getId())
                                .content(object.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.name").value("Имя")));
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/adopter-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(service).deleteAdopter(1L);
    }


    @Test
    void getAll() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new DogAdopter()));
        mockMvc.perform(MockMvcRequestBuilders.get("/adopter-dog/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }
    @Test
    void findAdopter() throws Exception{
        final String name = "Имя";
        final int birthday = 2000;
        final String phoneNumber = "55-55-55";
        final String email = "aaa@mail.ru";
        final String address = "Rostov";
        final long chatId = 123;
        final long id=1;

        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("birthday", birthday);
        object.put("phoneNumber", phoneNumber);
        object.put("email", email);
        object.put("address", address);
        object.put("chatId", chatId);
        object.put("id", id);
        DogAdopter adopter = new DogAdopter(id,name,birthday,phoneNumber,email,address,chatId,new Dog());
        adopter.setName(name);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(adopter));
        mockMvc.perform(get("/adopter-dog/"+adopter.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}


