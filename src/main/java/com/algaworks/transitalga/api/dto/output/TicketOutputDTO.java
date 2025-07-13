package com.algaworks.transitalga.api.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TicketOutputDTO {

    private Long id;

    private String description;

    private BigDecimal fineAmount;

    private OffsetDateTime occurrenceDate;
}
