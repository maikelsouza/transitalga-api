package com.algaworks.transitalga.api.assembler;

import com.algaworks.transitalga.api.dto.input.TicketInputDTO;
import com.algaworks.transitalga.api.dto.output.TicketOutputDTO;
import com.algaworks.transitalga.domain.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketAssembler {

    private final ModelMapper modelMapper;

    public Ticket toEntity(TicketInputDTO ticketInputDTO){
        return modelMapper.map(ticketInputDTO, Ticket.class);
    }

    public TicketOutputDTO toModel(Ticket ticket){
        return modelMapper.map(ticket, TicketOutputDTO.class);
    }

    public List<TicketOutputDTO> toCollectionModel(List<Ticket> tickets){
        return tickets.stream()
                .map(this::toModel)
                .toList();
    }
}
