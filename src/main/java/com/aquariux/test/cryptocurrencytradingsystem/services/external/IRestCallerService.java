package com.aquariux.test.cryptocurrencytradingsystem.services.external;

public interface IRestCallerService {
    Object get(String endpoint, String path, Class<?> responseType);
}
