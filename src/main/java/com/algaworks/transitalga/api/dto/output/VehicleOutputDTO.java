package com.algaworks.transitalga.api.dto.output;

import com.algaworks.transitalga.domain.enums.StatusVehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VehicleOutputDTO {


    private Long id;

    private OwnerOutputDTO owner;

    private String brand;

    private String model;

    private String plateNumber;

    private StatusVehicle status;

    private OffsetDateTime createDate;

    private OffsetDateTime seizureDate;
}
