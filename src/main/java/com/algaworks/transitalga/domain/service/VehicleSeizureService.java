package com.algaworks.transitalga.domain.service;

import com.algaworks.transitalga.domain.enums.StatusVehicle;
import com.algaworks.transitalga.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class VehicleSeizureService {

    private final VehicleRegisterService vehicleRegisterService;

    @Transactional
    public void seize(Long vehicleId){
        Vehicle vehicle = vehicleRegisterService.findById(vehicleId);
        vehicle.seize();
    }

    @Transactional
    public void releaseSeize(Long vehicleId){
        Vehicle vehicle = vehicleRegisterService.findById(vehicleId);
        vehicle.release();
    }
}
