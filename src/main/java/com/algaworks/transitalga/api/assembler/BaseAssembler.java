package com.algaworks.transitalga.api.assembler;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@NoArgsConstructor(force = true) // Garante um construtor sem argumentos
public abstract class BaseAssembler<E, I, O> {

    protected final ModelMapper modelMapper;

    public E toEntity(I inputDTO) {
        return modelMapper.map(inputDTO, getEntityClass());
    }

    public O toModel(E entity) {
        return modelMapper.map(entity, getOutputClass());
    }

    public List<O> toCollectionModel(List<E> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    protected abstract Class<E> getEntityClass();
    protected abstract Class<O> getOutputClass();
}
