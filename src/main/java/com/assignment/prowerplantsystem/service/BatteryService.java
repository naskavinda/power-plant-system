package com.assignment.prowerplantsystem.service;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.dto.BatteryWithReturnDetailsDTO;
import com.assignment.prowerplantsystem.util.Response;

import java.util.List;

public interface BatteryService {

    Response<List<BatteryDTO>> saveBatteries(List<BatteryDTO> batteryDTOS);

    List<BatteryWithReturnDetailsDTO> getBatteryWithReturnDetails(String from, String to);
}
