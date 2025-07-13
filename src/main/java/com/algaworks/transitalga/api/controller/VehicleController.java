package com.algaworks.transitalga.api.controller;

import com.algaworks.transitalga.api.assembler.VehicleAssembler;
import com.algaworks.transitalga.api.dto.input.VehicleInputDTO;
import com.algaworks.transitalga.api.dto.output.VehicleOutputDTO;
import com.algaworks.transitalga.domain.model.Vehicle;
import com.algaworks.transitalga.domain.service.VehicleRegisterService;
import com.algaworks.transitalga.domain.service.VehicleSeizureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping(value = "vehicles")
public class VehicleController {

    private final VehicleRegisterService service;

    private final VehicleAssembler vehicleAssembler;

    private final VehicleSeizureService vehicleSeizureService;

    @GetMapping
    public  List<VehicleOutputDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleOutputDTO> findById(@PathVariable Long id){

        VehicleOutputDTO vehicleOutputDTO = vehicleAssembler.toModel(service.findById(id));
        if (vehicleOutputDTO != null){
            return ResponseEntity.ok(vehicleOutputDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleOutputDTO save(@Valid @RequestBody VehicleInputDTO vehicleInputDTO){
        Vehicle vehicle = vehicleAssembler.toEntity(vehicleInputDTO);
        return service.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (service.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleOutputDTO> update(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle){
        if (service.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        vehicle.setId(id);
        VehicleOutputDTO vehicleOutputDTO = service.save(vehicle);
        return ResponseEntity.ok(vehicleOutputDTO);
    }


    @PutMapping("/{id}/seizerud")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void seize(@PathVariable Long id){
        vehicleSeizureService.seize(id);
    }

    @DeleteMapping("/{id}/seizerud")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void releaseSeize(@PathVariable Long id){
        vehicleSeizureService.releaseSeize(id);
    }

}
