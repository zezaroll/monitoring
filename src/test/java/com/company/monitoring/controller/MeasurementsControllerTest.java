package com.company.monitoring.controller;

import com.company.monitoring.dto.CreateMeasurementRequestDTO;
import com.company.monitoring.enums.WaterType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MeasurementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String POST_URL = "/api/v1/measurement";

    @Test
    void shouldInsertUserMeasurement() throws Exception {
        CreateMeasurementRequestDTO requestDTO = new CreateMeasurementRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setWater(10.5);
        requestDTO.setWaterType(WaterType.COLD);
        requestDTO.setGas(25.0);

          mockMvc.perform(MockMvcRequestBuilders.post(POST_URL)
                .content(objectMapper.writeValueAsString(requestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldReturnError_whenGasIsZero() throws Exception {
        CreateMeasurementRequestDTO requestDTO = new CreateMeasurementRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setWater(10.5);
        requestDTO.setWaterType(WaterType.COLD);
        requestDTO.setGas(0);

        mockMvc.perform(MockMvcRequestBuilders.post(POST_URL)
                .content(objectMapper.writeValueAsString(requestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(true));
    }

    @Test
    void shouldGetUserMeasurements() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/measurement/user/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}