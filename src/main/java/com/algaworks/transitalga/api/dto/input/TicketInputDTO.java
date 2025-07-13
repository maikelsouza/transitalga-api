package com.algaworks.transitalga.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TicketInputDTO {

    @NotBlank

    private String description;

    @Positive
    private BigDecimal fineAmount;
}
