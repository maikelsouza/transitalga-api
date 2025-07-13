package com.algaworks.transitalga.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OwnerInputDTO {

    @NotNull
    private Long id;
}
