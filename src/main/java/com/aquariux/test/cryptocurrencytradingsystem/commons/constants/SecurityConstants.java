package com.aquariux.test.cryptocurrencytradingsystem.commons.constants;

public class SecurityConstants {
    public static final String[] SECURITY_WHITE_LIST = {
            "/api/auth/**"
            , "/v3/api-docs/**"
            , "/swagger-ui/**"
            , "/swagger-ui.html"
            , "/actuator/**"
            , "/h2-console/**"};
    public static final class Header {
        public static final String CREDENTIAL_USER = "X-Credential-User";
    }
}
