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
import pet.shelter.controller.ReportController;
import pet.shelter.model.CatAdopter;
import pet.shelter.model.Report;
import pet.shelter.repository.CatAdopterRepository;
import pet.shelter.repository.ReportRepository;
import pet.shelter.services.CatAdopterService;
import pet.shelter.services.ReportService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @MockBean
    private ReportRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private ReportService service;

    @Test
    void findReport() throws Exception {

        Report report = new Report();
        report.setId(1L);
        service.save(report);
        Mockito.when(service.findById(anyLong())).thenReturn(report);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/file/{id}",1L))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id").value(1));


    }

    @Test
    void deleteReport() throws Exception {
        mockMvc.perform(delete("/file/{id}", 1))
                .andExpect(status().isOk());
        verify(service).delete(1L);
    }


    @Test
    void getAll() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new Report()));
        mockMvc.perform(MockMvcRequestBuilders.get("/file/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$").isArray());


    }
}

