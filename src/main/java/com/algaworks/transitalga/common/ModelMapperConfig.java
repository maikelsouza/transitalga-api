package com.algaworks.transitalga.common;

import com.algaworks.transitalga.api.dto.output.VehicleOutputDTO;
import com.algaworks.transitalga.domain.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Vehicle.class, VehicleOutputDTO.class)
                .addMappings(mapper -> mapper.map(Vehicle::getPlate, VehicleOutputDTO::setPlateNumber));

        return modelMapper;
    }


}
