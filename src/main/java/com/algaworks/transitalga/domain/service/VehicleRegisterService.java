package com.algaworks.transitalga.domain.service;

import com.algaworks.transitalga.api.assembler.VehicleAssembler;
import com.algaworks.transitalga.api.dto.output.VehicleOutputDTO;
import com.algaworks.transitalga.domain.enums.StatusVehicle;
import com.algaworks.transitalga.domain.exception.BusinessException;
import com.algaworks.transitalga.domain.exception.EntityNotFoundException;
import com.algaworks.transitalga.domain.model.Owner;
import com.algaworks.transitalga.domain.model.Vehicle;
import com.algaworks.transitalga.domain.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class VehicleRegisterService {


    private VehicleRepository repository;

    private OwnerRegisterService ownerRegisterService;

    private VehicleAssembler vehicleAssembler;



    @Transactional
    public VehicleOutputDTO save(Vehicle vehicle){

        if (vehicle.getId() != null){
            throw new BusinessException("The vehicle must not have a code.");
        }

        Owner owner = ownerRegisterService.findById(vehicle.getOwner().getId());
        vehicle.setOwner(owner);
        vehicle.setStatus(StatusVehicle.REGULAR);
        vehicle.setCreateDate(OffsetDateTime.now());
        boolean plateInUse = repository.findByPlate(vehicle.getPlate())
                .filter(o -> !o.equals(vehicle) )
                .isPresent();
        if (plateInUse){
            throw new BusinessException("There is already an vehicle registered with this plate.");
        }
        return vehicleAssembler.toModel(repository.save(vehicle));
    }

    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public List<VehicleOutputDTO> findAll(){
        return vehicleAssembler.toCollectionModel(repository.findAll());
    }

    public Vehicle findById(Long id){
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Vehicle not found.") );
    }


}
