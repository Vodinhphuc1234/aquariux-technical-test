package com.aquariux.test.cryptocurrencytradingsystem.mappers;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.PriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PriceMapper extends BaseMapper<Price, PriceResponse> {

}