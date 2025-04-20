package com.aquariux.test.cryptocurrencytradingsystem.mappers;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.TradingTransactionResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.TradingTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TradingTransactionMapper extends BaseMapper<TradingTransaction, TradingTransactionResponse> {

}