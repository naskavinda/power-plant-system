package com.assignment.prowerplantsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnBatteryDTO {

    private Integer totalCapacity;
    private Integer averageCapacity;
}
