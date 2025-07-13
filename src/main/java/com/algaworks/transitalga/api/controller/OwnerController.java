package com.algaworks.transitalga.api.controller;

import com.algaworks.transitalga.domain.model.Owner;
import com.algaworks.transitalga.domain.service.OwnerRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping(value = "owners")
public class OwnerController {

    private OwnerRegisterService service;

    @GetMapping()
    public List<Owner> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id){
        Owner owner = service.findById(id);
        if (owner != null){
            return ResponseEntity.ok(owner);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Owner save(@Valid  @RequestBody Owner owner){
        return service.save(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable Long id, @Valid @RequestBody Owner owner){
        if (!service.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        owner.setId(id);
        Owner ownerDb = service.save(owner);
        return ResponseEntity.ok(ownerDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (!service.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
