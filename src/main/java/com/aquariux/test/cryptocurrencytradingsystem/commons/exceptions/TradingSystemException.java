package com.aquariux.test.cryptocurrencytradingsystem.commons.exceptions;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import lombok.*;

@Getter
@Setter
@Builder
public class TradingSystemException extends BaseException {
    @Override
    protected void initDefaultType() {
        this.setDefaultType(ErrorConstants.ERROR_CODE.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected void initDefaultMessage() {
        this.setDefaultMessage(ErrorConstants.ERROR_MESSAGE.INTERNAL_SERVER_ERROR);
    }

    public TradingSystemException(String message) {
        super(message);
    }

    public TradingSystemException() {
        super();
    }

    public TradingSystemException(String type, String message) {
        super(type, message);
    }
}
