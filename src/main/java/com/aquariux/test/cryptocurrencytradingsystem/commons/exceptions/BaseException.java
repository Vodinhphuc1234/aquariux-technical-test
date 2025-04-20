package com.aquariux.test.cryptocurrencytradingsystem.commons.exceptions;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponseList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {
    private String type;
    private String message;

    private String defaultType;
    private String defaultMessage;

    protected abstract void initDefaultType();
    protected abstract void initDefaultMessage();

    public BaseException(String message) {
        initDefaultType();
        this.message = message;
        this.type = defaultType;
    }

    public BaseException() {
        initDefaultType();
        initDefaultMessage();
        this.message = defaultMessage;
        this.type = defaultType;
    }

    public BaseException(String type, String message) {
        this.message = message;
        this.type = type;
    }

    public BaseErrorResponseList toBaseErrorResponseList() {
        BaseErrorResponse errorResponse = BaseErrorResponse
                .builder()
                .code(type)
                .message(message)
                .build();
        return BaseErrorResponseList.builder()
                .errors(List.of(errorResponse))
                .build();
    }
}
