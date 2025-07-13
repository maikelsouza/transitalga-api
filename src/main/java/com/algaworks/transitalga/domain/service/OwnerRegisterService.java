package com.algaworks.transitalga.domain.service;

import com.algaworks.transitalga.domain.exception.BusinessException;
import com.algaworks.transitalga.domain.model.Owner;
import com.algaworks.transitalga.domain.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OwnerRegisterService {

    private OwnerRepository repository;

    @Transactional
    public Owner save(Owner owner){
        boolean emailInUse = repository.findByEmail(owner.getEmail())
                .filter(o -> !o.equals(owner) )
                .isPresent();
        if (emailInUse){
            throw new BusinessException("There is already an owner registered with this email.");
        }
        return repository.save(owner);
    }

    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Owner findById(Long id){
        return repository.findById(id)
                .orElseThrow( () -> new BusinessException("Owner not found.") );
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public List<Owner> findAll(){
        return repository.findAll();
    }
}
