package com.assignment.prowerplantsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryWithReturnDetailsDTO {

    private String name;
    private String postcode;
    private Integer capacity;
    private ReturnBatteryDTO returnBattery;
}
