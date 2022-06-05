package com.assignment.prowerplantsystem.service.impl;

import com.assignment.prowerplantsystem.dto.BatteryDTO;
import com.assignment.prowerplantsystem.dto.BatteryWithReturnDetailsDTO;
import com.assignment.prowerplantsystem.dto.ReturnBatteryDTO;
import com.assignment.prowerplantsystem.entity.Battery;
import com.assignment.prowerplantsystem.repository.BatteryRepository;
import com.assignment.prowerplantsystem.service.BatteryService;
import com.assignment.prowerplantsystem.util.Response;
import com.assignment.prowerplantsystem.util.ResponseDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    @Override
    public Response<List<BatteryDTO>> saveBatteries(List<BatteryDTO> batteryDTOS) {
        Set<String> names = batteryDTOS.stream().map(BatteryDTO::getName).collect(Collectors.toSet());
        if (!isContainDuplicateRecords(batteryDTOS, names)) {
            List<Battery> batteriesForNames = batteryRepository.findBatteriesByNameList(names.stream().toList());
            if (batteriesForNames.isEmpty()) {
                List<Battery> batteries = batteryDTOS.stream()
                        .map(this::buildBatteryEntity)
                        .toList();
                batteryRepository.saveAll(batteries);
                return Response.success(Collections.emptyList());
            } else {
                return new Response<>(ResponseDetails.E1001.getCode(), ResponseDetails.E1001.getDescription(), mapToBatteryDTO(batteriesForNames));
            }
        }
        return new Response<>(ResponseDetails.E1002.getCode(), ResponseDetails.E1002.getDescription());
    }

    private List<BatteryDTO> mapToBatteryDTO(List<Battery> batteriesForNames) {
        return batteriesForNames.stream()
                .map(this::buildBatteryDTO)
                .toList();
    }

    private BatteryDTO buildBatteryDTO(Battery battery) {
        return BatteryDTO.builder()
                .name(battery.getName())
                .postcode(battery.getPostcode())
                .capacity(battery.getCapacity())
                .build();
    }

    private boolean isContainDuplicateRecords(List<BatteryDTO> batteryDTOs, Set<String> names) {
        return batteryDTOs.size() != names.size();
    }


    private Battery buildBatteryEntity(BatteryDTO batteryDTO) {
        return Battery.builder()
                .name(batteryDTO.getName())
                .postcode(batteryDTO.getPostcode())
                .capacity(batteryDTO.getCapacity())
                .build();
    }

    @Override
    public List<BatteryWithReturnDetailsDTO> getBatteryWithReturnDetails(String from, String to) {
        List<Battery> batteries = batteryRepository.findBatteriesByPostcodeRage(from, to);
        return batteries.stream().
                sorted(Comparator.comparing(Battery::getName).thenComparing(Battery::getName))
                .map(this::mapToBatteryWithReturnDetails)
                .toList();
    }

    private BatteryWithReturnDetailsDTO mapToBatteryWithReturnDetails(Battery battery) {
       return BatteryWithReturnDetailsDTO.builder()
                .name(battery.getName())
                .postcode(battery.getPostcode())
                .capacity(battery.getCapacity())
                .returnBattery(mapToReturnBatteryDTO(battery))
                .build();
    }

    private ReturnBatteryDTO mapToReturnBatteryDTO(Battery battery) {
        if (battery.getReturnBattery()!= null) {
            return ReturnBatteryDTO.builder()
                    .averageCapacity(battery.getReturnBattery().getAverageCapacity())
                    .totalCapacity(battery.getReturnBattery().getTotalCapacity())
                    .build();
        }
        return null;
    }
}
