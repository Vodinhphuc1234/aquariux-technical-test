package com.aquariux.test.cryptocurrencytradingsystem.mappers;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import org.springframework.data.domain.Page;

import java.util.List;

public abstract class BaseMapper<E, D> {

    public abstract D toDTO(E source);

    public abstract E toEntity(D source);

    public abstract List<D> toDTOs(List<E> source);

    public abstract List<E> toEntities(List<D> source);

    public CustomPage<D> toDTOs(Page<E> source) {
        List<D> dtos = toDTOs(source.getContent());
        return new CustomPage<>(source.getNumber(), source.getTotalElements(), source.getTotalPages(), source.getSize(), dtos);
    }
}