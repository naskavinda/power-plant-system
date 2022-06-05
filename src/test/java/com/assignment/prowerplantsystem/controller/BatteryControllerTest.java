package com.assignment.prowerplantsystem.controller;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.service.impl.BatteryServiceImpl;
import com.assignment.prowerplantsystem.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.assignment.prowerplantsystem.util.ResponseDetails.E1001;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryController.class)
class BatteryControllerTest {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BatteryServiceImpl batteryService;

    private BatteryDTO BATTERY_DTO_1;

    @BeforeEach
    void setUp() {
        BATTERY_DTO_1 = BatteryDTO.builder()
                .name("Cannington")
                .postcode("6107")
                .capacity(13500)
                .build();
    }

    @Test
    void shouldReturn_successResponse_whenInputIsValid() throws Exception {

        List<BatteryDTO> batteryDTOList = List.of(BATTERY_DTO_1);
        Response<List<BatteryDTO>> success = Response.success(Collections.emptyList());
        Mockito.when(batteryService.saveBatteries(batteryDTOList)).thenReturn(success);
        this.mockMvc.perform(
                        post("/api/batteries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(batteryDTOList))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.code", containsString("S1000")))
                .andExpect(jsonPath("$.description", containsString("Success")));
    }

    @Test
    void shouldReturn_E1001Code_whenInputContainAllReadyExistRecode() throws Exception {

        List<BatteryDTO> batteryDTOList = List.of(BATTERY_DTO_1);
        Response<List<BatteryDTO>> success = new Response(E1001.getCode(), E1001.getDescription(), List.of(BATTERY_DTO_1));
        Mockito.when(batteryService.saveBatteries(batteryDTOList)).thenReturn(success);
        this.mockMvc.perform(
                        post("/api/batteries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(batteryDTOList))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.code", containsString("E1001")))
                .andExpect(jsonPath("$.data[0].name", containsString(BATTERY_DTO_1.getName())));
    }
}