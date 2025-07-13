package com.algaworks.transitalga.domain.model;

import com.algaworks.transitalga.domain.enums.StatusVehicle;
import com.algaworks.transitalga.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Vehicle {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Owner owner;

    private String brand;

    private String model;

    private String plate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusVehicle status;

    private OffsetDateTime createDate;

    private OffsetDateTime seizureDate;

    public Ticket addTicket(Ticket ticket){
        ticket.setOccurrenceDate(OffsetDateTime.now());
        ticket.setVehicle(this);
        getTickets().add(ticket);
        return ticket;
    }

    public void seize(){
        if (isSeized()){
            throw new BusinessException("Vehicle already is seized");
        }
        this.setStatus(StatusVehicle.SEIZED);
        this.setSeizureDate(OffsetDateTime.now());
    }

    public void release() {
        if (!isSeized()) {
            throw new BusinessException("Vehicle is not seized");
        }
        this.setStatus(StatusVehicle.REGULAR);
        this.setSeizureDate(null);
    }

    public boolean isSeized(){
        return StatusVehicle.SEIZED.equals(getStatus());
    }
}
