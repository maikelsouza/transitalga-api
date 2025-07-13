package com.algaworks.transitalga.api.assembler;

import com.algaworks.transitalga.api.dto.input.VehicleInputDTO;
import com.algaworks.transitalga.api.dto.output.VehicleOutputDTO;
import com.algaworks.transitalga.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VehicleAssembler {

    private final ModelMapper modelMapper;

    public Vehicle toEntity(VehicleInputDTO vehicleInputDTO){
        return modelMapper.map(vehicleInputDTO, Vehicle.class);
    }

    public VehicleOutputDTO toModel(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleOutputDTO.class);
    }

    public List<VehicleOutputDTO> toCollectionModel(List<Vehicle> vehicles){
        return vehicles.stream()
                .map(this::toModel)
                .toList();
    }

}
