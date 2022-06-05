package com.assignment.prowerplantsystem.service.impl;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.entity.Battery;
import com.assignment.prowerplantsystem.repository.BatteryRepository;
import com.assignment.prowerplantsystem.util.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BatteryServiceImplTest {

    @Mock
    private BatteryRepository batteryRepository;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    private BatteryDTO BATTERY_DTO_1;
    private Battery BATTERY_1;

    @BeforeEach
    void setUp() {
        BATTERY_DTO_1 = BatteryDTO.builder()
                .name("Cannington")
                .postcode("6107")
                .capacity(13500)
                .build();

        BATTERY_1 = Battery.builder()
                .name("Cannington")
                .postcode("6107")
                .capacity(13500)
                .returnBattery(null)
                .build();
    }

    @DisplayName("Save Batteries success scenario")
    @Test
    void shouldReturn_successResponse_forSaveBatteries_whenInput_isValid() {
        given(batteryRepository.findBatteriesByNameList(List.of("Cannington"))).willReturn(Collections.emptyList());
        given(batteryRepository.saveAll(List.of(BATTERY_1))).willReturn(List.of());

        Response<List<BatteryDTO>> listResponse = batteryService.saveBatteries(List.of(BATTERY_DTO_1));
        Assertions.assertEquals("S1000", listResponse.getCode());
        Assertions.assertEquals(0, listResponse.getData().size());
    }

    @DisplayName("Save Batteries Input has duplicate records scenario")
    @Test
    void shouldReturn_successResponse_forSaveBatteries_whenInput_hasDuplicateRecords() {

        Response<List<BatteryDTO>> listResponse = batteryService.saveBatteries(List.of(BATTERY_DTO_1, BATTERY_DTO_1));
        Assertions.assertEquals("E1002", listResponse.getCode());
        Assertions.assertNull(listResponse.getData());
    }

    @DisplayName("Save Batteries Input has already exist records scenario")
    @Test
    void shouldReturn_successResponse_forSaveBatteries_whenInput_hasAlreadyExistRecords() {
        given(batteryRepository.findBatteriesByNameList(List.of("Cannington"))).willReturn(List.of(BATTERY_1));

        Response<List<BatteryDTO>> listResponse = batteryService.saveBatteries(List.of(BATTERY_DTO_1));
        Assertions.assertEquals("E1001", listResponse.getCode());
        Assertions.assertEquals(1, listResponse.getData().size());
        Assertions.assertEquals(BATTERY_DTO_1.getName(), listResponse.getData().get(0).getName());
    }
}