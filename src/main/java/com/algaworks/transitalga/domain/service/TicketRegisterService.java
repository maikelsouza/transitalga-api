package com.algaworks.transitalga.domain.service;

import com.algaworks.transitalga.domain.model.Ticket;
import com.algaworks.transitalga.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class TicketRegisterService {


    private final VehicleRegisterService vehicleRegisterService;


    @Transactional
    public Ticket save(Long vehicleId, Ticket newTicket){

        Vehicle vehicle = vehicleRegisterService.findById(vehicleId);
        return vehicle.addTicket(newTicket);
    }

    public List<Ticket> findByIdVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRegisterService.findById(vehicleId);
        return vehicle.getTickets();
    }
}
