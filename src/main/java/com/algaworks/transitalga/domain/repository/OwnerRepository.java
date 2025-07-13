package com.algaworks.transitalga.domain.repository;

import com.algaworks.transitalga.domain.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByNameContaining(String name);

    Optional<Owner> findByEmail(String Email);

}

