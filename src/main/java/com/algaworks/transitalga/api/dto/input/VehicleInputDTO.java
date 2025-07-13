package com.algaworks.transitalga.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleInputDTO {

    @NotBlank
    @Size(max = 20)
    private String brand;

    @NotBlank
    @Size(max = 20)
    private String model;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String plateNumber;

    @NotNull
    @Valid
    private OwnerInputDTO owner;
}
