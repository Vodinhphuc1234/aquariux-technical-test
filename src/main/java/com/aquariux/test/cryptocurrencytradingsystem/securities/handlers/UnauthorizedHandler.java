package com.aquariux.test.cryptocurrencytradingsystem.securities.handlers;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.commons.utils.ConverterUtils;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponseList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UnauthorizedHandler implements AccessDeniedHandler {
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        BaseErrorResponse errorResponse = BaseErrorResponse.builder()
                .code(ErrorConstants.ERROR_CODE.FORBIDDEN)
                .message(ErrorConstants.ERROR_MESSAGE.FORBIDDEN)
                .build();
        BaseErrorResponseList baseErrorResponseList = BaseErrorResponseList.builder()
                .errors(List.of(errorResponse)).build();
        ConverterUtils.OBJECT_MAPPER.writeValue(response.getOutputStream(), baseErrorResponseList);
    }
}
