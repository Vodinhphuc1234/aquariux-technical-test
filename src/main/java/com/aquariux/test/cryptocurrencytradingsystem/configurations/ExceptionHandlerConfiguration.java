package com.aquariux.test.cryptocurrencytradingsystem.configurations;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.commons.exceptions.TradingSystemException;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponseList;
import jakarta.transaction.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfiguration {
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseErrorResponseList handleSystemError(TradingSystemException ex) {
        log.error("TradingSystemException: {} - {}", ex.getType(), ex.getMessage(), ex);
        return ex.toBaseErrorResponseList();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public BaseErrorResponseList handlerException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException: {}", ex.getMessage(), ex);
        BaseErrorResponse errorResponse = BaseErrorResponse.builder()
                .code(ErrorConstants.ERROR_CODE.UNPROCESSABLE_ENTITY)
                .message(ex.getMessage())
                .build();
        return BaseErrorResponseList.builder()
                .errors(List.of(errorResponse)).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseErrorResponseList handlerException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        BaseErrorResponse errorResponse = BaseErrorResponse.builder()
                .code(ErrorConstants.ERROR_CODE.INTERNAL_SERVER_ERROR)
                .message(ErrorConstants.ERROR_MESSAGE.INTERNAL_SERVER_ERROR)
                .build();
        return BaseErrorResponseList.builder()
                .errors(List.of(errorResponse)).build();
    }
}
