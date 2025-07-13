package com.algaworks.transitalga.api.controller;

import com.algaworks.transitalga.api.assembler.TicketAssembler;
import com.algaworks.transitalga.api.dto.input.TicketInputDTO;
import com.algaworks.transitalga.api.dto.output.TicketOutputDTO;
import com.algaworks.transitalga.domain.model.Ticket;
import com.algaworks.transitalga.domain.service.TicketRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicles/{vehicleId}/tickets")
public class TicketController {

    private final TicketRegisterService service;

    private final TicketAssembler ticketAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketOutputDTO register(@PathVariable Long vehicleId, @Valid @RequestBody TicketInputDTO ticketInputDTO){

        Ticket ticket = service.save(vehicleId, ticketAssembler.toEntity(ticketInputDTO));

        return ticketAssembler.toModel(ticket);
    }

    @GetMapping
    public List<TicketOutputDTO> findByIdVehicle(@PathVariable Long vehicleId){

        List<Ticket> tickets = service.findByIdVehicle(vehicleId);
        return ticketAssembler.toCollectionModel(tickets);
    }
}
