package com.assignment.prowerplantsystem.controller;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.service.impl.BatteryServiceImpl;
import com.assignment.prowerplantsystem.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    private final BatteryServiceImpl batteryService;

    public BatteryController(BatteryServiceImpl batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping()
    public ResponseEntity<Response<List<BatteryDTO>>> saveBatteries(@RequestBody List<BatteryDTO> batteryDTOs) {

        Response<List<BatteryDTO>> response = batteryService.saveBatteries(batteryDTOs);
        return ResponseEntity.ok(response);
    }
}
