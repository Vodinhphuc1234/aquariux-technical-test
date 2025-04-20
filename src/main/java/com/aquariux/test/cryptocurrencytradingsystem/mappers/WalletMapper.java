package com.aquariux.test.cryptocurrencytradingsystem.mappers;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.WalletResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WalletMapper extends BaseMapper<Wallet, WalletResponse> {

}