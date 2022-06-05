package com.assignment.prowerplantsystem.service.impl;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.entity.Battery;
import com.assignment.prowerplantsystem.repository.BatteryRepository;
import com.assignment.prowerplantsystem.service.BatteryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    public Boolean saveBatteries(List<BatteryDTO> batteryDTOS) {
        List<Battery> batteries = batteryDTOS.stream()
                .map(this::buildBatteryEntity)
                .toList();

        List<Battery> savedBatteryList = batteryRepository.saveAll(batteries);
        return !savedBatteryList.isEmpty();
    }

    private Battery buildBatteryEntity(BatteryDTO batteryDTO) {
        return Battery.builder()
                .name(batteryDTO.getName())
                .postcode(batteryDTO.getPostcode())
                .capacity(batteryDTO.getCapacity())
                .build();
    }
}
