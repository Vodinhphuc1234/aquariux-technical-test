package com.aquariux.test.cryptocurrencytradingsystem.securities.filters;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class ForwardHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String userId = request.getHeader(SecurityConstants.Header.CREDENTIAL_USER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (StringUtils.isNotBlank(userId)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, List.of((GrantedAuthority) () -> "ROLE_USER")
            );

            log.info("Authentication successful. Logged in user id : {}", userId);
            securityContext.setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
