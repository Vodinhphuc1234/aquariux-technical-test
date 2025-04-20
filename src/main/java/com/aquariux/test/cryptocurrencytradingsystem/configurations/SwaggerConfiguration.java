package com.aquariux.test.cryptocurrencytradingsystem.configurations;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.SecurityConstants;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vodinhphuc on 24/12/2022
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfiguration {

    private String appName;

    private String appDescription;

    private String appVersion;

    private String appLicense;

    private String appLicenseUrl;

    private String contactName;

    private String contactUrl;

    private String contactMail;

    @Bean
    public OpenAPI openAPI() {

        final Info apiInformation = getApiInformation();
        final OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(apiInformation);

        return openAPI;
    }
//    @Bean
//    public GroupedOpenApi officialApi() {
//        return GroupedOpenApi.builder().group("official-api").pathsToExclude("/actuator","/actuator/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi actuatorApi() {
//        return GroupedOpenApi.builder()
//                .group("actuator-api")
//                .pathsToMatch("/actuator","/actuator/**")
//                .build();
//    }

    @Bean
    public OperationCustomizer customizeHeader() {
        return (operation, handlerMethod) -> {
            Parameter header = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name(SecurityConstants.Header.CREDENTIAL_USER)
                    .description("X Credential User ID")
                    .required(true);
            operation.addParametersItem(header);
            return operation;
        };
    }

    private Info getApiInformation() {

        final License license = new License();
        license.setName(appLicense);
        license.setUrl(appLicenseUrl);

        final Contact contact = new Contact();
        contact.setName(contactName);
        contact.setUrl(contactUrl);
        contact.setEmail(contactMail);


        final Info info = new Info();
        info.setTitle(appName);
        info.setVersion(appVersion);
        info.setDescription(appDescription);
        info.setLicense(license);
        info.setContact(contact);

        return info;
    }

}

