package com.aquariux.test.cryptocurrencytradingsystem.securities.handlers;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.commons.utils.ConverterUtils;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseErrorResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;


/**
 * Created by vodinhphuc on 24/12/2022
 */
@Component
@RequiredArgsConstructor
public class UnauthenticatedHandler implements AuthenticationEntryPoint {
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		BaseErrorResponse errorResponse = BaseErrorResponse.builder()
				.code(ErrorConstants.ERROR_CODE.UNAUTHORIZED)
				.message(ErrorConstants.ERROR_MESSAGE.UNAUTHORIZED)
				.build();
		BaseErrorResponseList baseErrorResponseList = BaseErrorResponseList.builder()
				.errors(List.of(errorResponse)).build();
		ConverterUtils.OBJECT_MAPPER.writeValue(response.getOutputStream(), baseErrorResponseList);
	}
}
